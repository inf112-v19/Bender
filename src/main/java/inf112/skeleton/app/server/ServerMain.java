package inf112.skeleton.app.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.*;

import com.badlogic.gdx.Game;
import com.google.gson.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ServerMain extends WebSocketServer {
    private JsonParser json = new JsonParser();
    private Random r = new Random();

    private HashMap<WebSocket, GameRoom> userRoomPairs = new HashMap<>();

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
        userRoomPairs.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        String[] messageData = message.split(" ", 2);
        System.out.println(Arrays.toString(messageData));
        System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
        if (messageData[0].equals("CREATEROOM")) {
            GameRoom gameRoom = createRoom();
            conn.send("INFO {\"message\":\"Room created successfully\"}");
            userRoomPairs.put(conn, gameRoom);
            conn.send(String.format("ROOM {\"roomId\":\"%s\"}", gameRoom.getRoomId()));
            return;
        }
        else if (messageData[0].equals("JOINROOM")) {
            JsonElement data = json.parse(messageData[1]);
            String roomId = data.getAsJsonObject().get("roomId").getAsString();
            for (GameRoom gameRoom : userRoomPairs.values()) {
                if (gameRoom.getRoomId().equals(roomId)) {
                    conn.send("INFO {\"message\":\"Room joined successfully\"}");
                    userRoomPairs.put(conn, gameRoom);
                    conn.send(String.format("ROOM {\"roomId\":\"&s\"}", roomId));
                    return;
                }
            }
            conn.send("ERROR {\"message\":\"Room was not found!\"}");
//        if (message.equals("GETBOARD")){
//            // TODO: generate a board and send to the client.
        } else {
            conn.send("ERROR {\"message\":\"This command has not yet been implemented\"}");
        }
    }

    private GameRoom createRoom() {
        String roomId;
        loop:
        while (true) {
            roomId = Integer.toHexString(r.nextInt());
            for (GameRoom gameRoom : userRoomPairs.values()) {
                if (gameRoom.getRoomId().equals(roomId)) {
                    continue loop;
                }
            }
            break;
        }
        return new GameRoom(roomId);
    }

    public HashMap<WebSocket, GameRoom> getUserRoomPairs() {
        return userRoomPairs;
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
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
        while(sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("stop")) break;
            server.broadcast(next);
        }
        server.stop();
    }
}