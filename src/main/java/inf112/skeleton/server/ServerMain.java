package inf112.skeleton.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import inf112.skeleton.app.utils.SerialFormat;
import javafx.util.Pair;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ServerMain extends WebSocketServer {

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
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
//        if (message.equals("GETBOARD")){
//            // TODO: generate a board and send to the client.
//        } else {
            conn.send("ERROR {\"message\":\"This command has not yet been implemented\"}");
//        }
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
        // TODO: Figure out and handle errors
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "0.0.0.0";
        int port = 8887;

        // TODO: Initialize a roborally game room

        WebSocketServer server = new ServerMain(new InetSocketAddress(host, port));
        server.start();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String next = sc.next();
            if (next.equals("stop")) break;
            server.broadcast(next);
        }
        server.stop();
    }
}