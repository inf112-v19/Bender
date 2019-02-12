package inf112.skeleton.app;

import inf112.skeleton.app.interfaces.API;
import inf112.skeleton.app.interfaces.IAction;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class RemoteServerHandler extends WebSocketClient {

    private final IAction handler;

    public RemoteServerHandler(URI serverUri, Draft draft, IAction handler) {
        super(serverUri, draft);
        this.handler = handler;
    }

    public RemoteServerHandler(URI serverURI, IAction handler) {
        super(serverURI);
        this.handler = handler;
    }

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
        System.out.println("received message: " + message);
        if (message.substring(0, 8).equals("BOARD")) {
            handler.handleBOARD(message.substring(9));
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

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        RemoteServerHandler client;
        mainHandler handler = new mainHandler();
        Scanner sc = new Scanner(System.in);
        while (true) {
            client = new RemoteServerHandler(new URI("ws://localhost:8887"), handler);
            client.handler.handleINFO("Connecting...");
            client.connectBlocking();
            if (client.isOpen()) {
                client.handler.handleINFO("Connected!");
                break;
            } else {
                client.handler.handleERROR("Failed to connect... retry? ([y]/n)");
                if (sc.next().charAt(0) == 'n') {
                    return;
                }
            }
        }
        while(sc.hasNext()) {
            String next = sc.next();
            if (next.equals("stop")) break;
            client.send(next);
        }
        client.closeBlocking();
    }

    public static class mainHandler implements IAction {
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
        public void handleBOARD(String board) {

        }
    }
}