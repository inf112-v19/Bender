package inf112.skeleton.app.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.*;

import com.google.gson.*;
import inf112.skeleton.app.core.board.Board;

import inf112.skeleton.app.core.cards.IProgramCard;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ServerMain extends WebSocketServer {
    private JsonParser json = new JsonParser();
    private Random r = new Random();
    private Gson gson = new Gson();

    //    private HashMap<WebSocket, GameRoom> userRoomPairs = new HashMap<>();
    private ArrayList<WebSocket> webSocket = new ArrayList<>();
    private GameRoom gameRoom = new GameRoom("SingleRoom");

    public ServerMain(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // conn.send("Welcome to the server!"); //This method sends a message to the new client
        // broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
        webSocket.add(conn);
        System.out.println("total connections: " + webSocket.size());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);

//        userRoomPairs.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        String[] messageData = message.split(" ", 2);
        if (messageData[0].equals("CREATEROOM")) {
            GameRoom gameRoom = createRoom();
            conn.send("INFO {\"message\":\"Room created successfully\"}");
            this.gameRoom = gameRoom;
            webSocket.add(conn);
            conn.send(String.format("ROOM {\"roomId\":\"%s\"}", gameRoom.getRoomId()));
            return;
        } else if (messageData[0].equals("JOINROOM")) {
            JsonElement data = json.parse(messageData[1]);
            String roomId = data.getAsJsonObject().get("roomId").getAsString();
            if (gameRoom != null) {
                conn.send("INFO {\"message\":\"Room joined successfully\"}");
                webSocket.add(conn);
                conn.send(String.format("ROOM {\"roomId\":\"%s\"}", roomId));
                return;
            }
            conn.send("ERROR {\"message\":\"Room was not found!\"}");
        } else if (messageData[0].equals("UPDATEBOARD")) {
            System.out.println("Board updated");
            Board board = gson.fromJson(messageData[1], Board.class);
            try {
                this.gameRoom.updateBoard(board);
            }catch (RuntimeException e) {
                System.out.println("wow");
            }
        } else if (messageData[0].equals("CARDS")) {
            JsonElement data = json.parse(messageData[1]);
            ArrayDeque<IProgramCard> chosenCards = gson.fromJson(data, ArrayDeque.class);
            try {
                addCards(conn, chosenCards);
            } catch (IllegalStateException ee) {
                System.out.println("wow");
            }
        } else if (message.equals("RESPONSE")) {
            gameRoom.setTotalConnections(webSocket.size());
            if (gameRoom.getStatus()) {
                String data = gson.toJson(gameRoom.getResponseMap());
                this.broadcast("SERVERRESPONSE " + data);
                gameRoom.setStatus(false);
            }
        } else if (message.equals("CLEAR")) {
            gameRoom.clearCards();
        } else {
            System.out.println(message);
            conn.send("ERROR {\"message\":\"This command has not yet been implemented\"}");
        }
    }

    public void addCards(WebSocket conn, ArrayDeque<IProgramCard> chosenCards) {
        gameRoom.addChosenCards(conn, chosenCards);
    }


    private GameRoom createRoom() {

        return new GameRoom("SingleRoom");
    }


    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred:" + ex);
        // TODO: Figure out and handle errors
    }


    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "0.0.0.0";
        int port = 8887;

        WebSocketServer server = new ServerMain(new InetSocketAddress(host, port));
        server.start();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("stop")) break;
            server.broadcast(next);
        }
        server.stop();
    }
}