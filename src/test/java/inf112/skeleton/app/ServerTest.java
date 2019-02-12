package inf112.skeleton.app;

import inf112.skeleton.server.ServerMain;
import org.junit.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class ServerTest {

    private ServerMain server;
    private RemoteServerHandler client;

    @Before
    public void setUp() throws URISyntaxException {
        server = new ServerMain(new InetSocketAddress("localhost", 8887));
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
