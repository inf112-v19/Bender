package inf112.skeleton.app;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import inf112.skeleton.app.server.RemoteServerHandler;
import inf112.skeleton.app.server.ServerMain;
import org.junit.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

@Ignore
public class ServerTest {

    private ServerMain server;
    private RemoteServerHandler client;
    private WebSocket conn;

    @Before
    public void setUp() throws URISyntaxException {
        server = new ServerMain(new InetSocketAddress("localhost", 8887)) {
            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                super.onOpen(conn, handshake);
                ServerTest.this.conn = conn;
            }
        };
        server.start();
        client = new RemoteServerHandler(null);
    }

    @Test
    public void clientConnectToServer() {
        try {
            client.connect();
        } catch (InterruptedException e) {
            fail();
        }
    }

    @After
    public void tearDown() throws InterruptedException, IOException {
        client.close();
        server.stop();
    }
}
