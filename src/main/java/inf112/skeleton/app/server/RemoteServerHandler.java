package inf112.skeleton.app.server;

import com.google.gson.Gson;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.player.IPlayer;
import inf112.skeleton.app.core.player.Player;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.Scanner;

public class RemoteServerHandler extends API {

    private static WebSocketClient client;
    private static Gson json = new Gson();

    public RemoteServerHandler(IAction handler) throws URISyntaxException {
        super(handler);
        newClient();
        connectClient();
    }

    private WebSocketClient newClient() throws URISyntaxException {
        return client = new WSC(new URI("ws://localhost:8887"));
    }

    @Override
    public void getBoard() {
        client.send("GETBOARD");
    }

    @Override
    public void drawCard() {
        client.send("DRAWCARD");
    }

    @Override
    public void createRoom() {
        client.send("CREATEROOM");
    }

    @Override
    public void getDeck() {
    }

    @Override
    public void chooseCards(int[] cardIndices) {

    }

    @Override
    public void joinRoom(String id) {
        client.send(String.format("JOINROOM {\"roomId\":\"%s\"}", id));
    }

    public WebSocketClient getClient() {
        return client;
    }
    public void connectClient() {
        client.connect();
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        RemoteServerHandler client;
        mainHandler handler = new mainHandler();
        Scanner sc = new Scanner(System.in);
        client = new RemoteServerHandler(handler);
        while (true) {
            client.handler.handleINFO("Connecting...");
            client.connect();
            if (client.isOpen()) {
                client.handler.handleINFO("Connected!");
                break;
            } else {
                client.handler.handleERROR("Failed to connect... retry? ([y]/n)");
                if (sc.nextLine().charAt(0) == 'n') {
                    return;
                }
            }

            client.newClient();
        }
        while(sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("stop")) break;
            client.client.send(next);
        }
        client.close();
    }

    public void close() throws InterruptedException {
        client.closeBlocking();
    }

    private boolean isOpen() {
        return client.isOpen();
    }

    @Override
    public void connect() throws InterruptedException {
        client.connectBlocking();
    }

    public static class mainHandler implements IAction {
        boolean received = false;
        public HashMap<Player, ArrayDeque<IProgramCard>> playerCardMap;
        @Override

        public void handleCards(ArrayDeque<IProgramCard> cards) {
            String cardsAsString = json.toJson(cards);
            client.send("CARDS " + cardsAsString);
        }


        @Override
        public void handleERROR(String message) {
            System.err.println("ERROR: "+message);
        }

        @Override
        public void handleWARNING(String message) {
            System.out.println("WARNING: "+message);
        }

        @Override
        public void handleINFO(String message) {
            System.out.println("INFO: "+message);
        }

        @Override
        public void handleBoard(IBoard board) {
        }

        @Override
        public void handleROOM(String roomId) {
            System.out.println("ROOM: "+roomId);
        }

        public void handleServerResponse() {
            client.send("RESPONSE");
        }

        @Override
        public void received(boolean b) {
            received = b;
        }

        public void handleBoardUpdate(Board board) {
            client.send("UPDATEBOARD " + json.toJson(board));
        }
        @Override
        public void updateCards(HashMap hashMap) {
            this.playerCardMap = hashMap;
        }

        public boolean getReceived() {
            return this.received;
        }
    }

    /**
     * WSC class for the websocketclient implementation
     *
     */
    private class WSC extends WebSocketClient {
        public WSC(URI serverUri) {
            super(serverUri);
        }
        public boolean received;

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            // TODO: Join room and setup local game using @handler
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            System.out.println("closed with exit code " + code + " additional info: " + reason);

        }

        @Override
        public void onMessage(String message) {
            String[] messageData = message.split(" ", 2);
            System.out.println("received message: " + message);
            if (messageData[0].equals("BOARD")) {
                handler.handleBoard(json.fromJson(messageData[1], Board.class));
            }
            if (messageData[0].equals("INFO")) {
                handler.handleINFO(json.toJsonTree(messageData[1]).getAsJsonObject().get("message").getAsString());
            }
            if (messageData[0].equals("ROOM")) {
                handler.handleROOM(json.toJsonTree(messageData[1]).getAsJsonObject().get("roomId").getAsString());
            }
            if (messageData[0].equals("SERVERRESPONSE")) {
                handler.received(true);
                handler.updateCards(json.fromJson(messageData[1], HashMap.class));
                System.out.println("client received");
                client.send("CLEAR");
            }
        }

        @Override
        public void onMessage(ByteBuffer message) {
            System.out.println("received ByteBuffer");
        }

        @Override
        public void onError(Exception ex) {
            handler.handleERROR(String.valueOf(ex));
        }
    }
}