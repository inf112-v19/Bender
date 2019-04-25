package inf112.skeleton.app.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.*;

import com.google.gson.*;
import com.sun.security.ntlm.Server;
import inf112.skeleton.app.core.cards.IProgramCard;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ServerMain extends WebSocketServer {
    private JsonParser json = new JsonParser();
    private Random r = new Random();
    private Gson gson = new Gson();

    //    private HashMap<WebSocket, GameRoom> userRoomPairs = new HashMap<>();
    private List<WebSocket> webSocket;
    private GameRoom gameRoom = new GameRoom("SingleRoom");

    public ServerMain(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // conn.send("Welcome to the server!"); //This method sends a message to the new client
        // broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
//        userRoomPairs.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        String[] messageData = message.split(" ", 2);
        System.out.println(Arrays.toString(messageData));
        System.out.println("received message from " + conn.getRemoteSocketAddress() + ": " + message);
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
        } else if (message.equals("GETBOARD")) {
            // TODO: generate a board and send to the client.
        } else if (messageData[0].equals("CARDS")) {
            System.out.println(conn.getRemoteSocketAddress());
            System.out.println("received cards");
            System.out.println("message :" + message);
            JsonElement data = json.parse(messageData[1]);
            ArrayDeque<IProgramCard> chosenCards = gson.fromJson(data, ArrayDeque.class);
            System.out.println("chosen cards: " + chosenCards);
            System.out.println("game room: " + gameRoom.getRoomId());
            try {
                addCards(conn, chosenCards);
            } catch (IllegalStateException ee ) {
                System.out.println("wow");
            }
        } else if (message.equals("RESPONSE")) {
            System.out.println(gameRoom.getStatus());
            if (gameRoom.getStatus()) {
                System.out.println("status passed");
                String data = gson.toJson(gameRoom.getResponseMap());
                System.out.println("seding " + data);
                this.broadcast("SERVERRESPONSE " + data);
            }

        }else{
            conn.send("ERROR {\"message\":\"This command has not yet been implemented\"}");
        }
    }
    public void addCards(WebSocket conn, ArrayDeque<IProgramCard> chosenCards) {
        gameRoom.addChosenCards(conn, chosenCards);
    }


    private GameRoom createRoom() {

        return new GameRoom("SingleRoom");
    }

//    public HashMap<WebSocket, GameRoom> getUserRoomPairs() {
//        return userRoomPairs;
//    }

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