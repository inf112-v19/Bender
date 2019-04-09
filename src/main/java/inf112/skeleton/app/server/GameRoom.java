package inf112.skeleton.app.server;

import com.google.gson.Gson;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.player.Player;
import org.java_websocket.WebSocket;

import java.util.HashMap;
import java.util.List;

public class GameRoom {

    private String roomId;
    private Board board;
    private HashMap<Player, WebSocket> connections;

    private Gson json = new Gson();

    public GameRoom(String roomId) {
        this.roomId = roomId;
        board = new Board("empty", 10, 10);
        connections = new HashMap<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public void startGame(List<WebSocket> sockets) {
        for (WebSocket socket : sockets) {
            Player player = new Player(socket.getRemoteSocketAddress().toString());
            connections.put(player, socket);
            socket.send("BOARD " + json.toJson(board));
            socket.send("PLAYER " + json.toJson(player));
        }
        // deal cards to players
    }

    public void round() {
        // deal cards to all players
        // wait for players to pick cards
        // receive cards
        // execute cards
        //  - send moves to all clients
        // wait for everyone to finish
        // send updated deck to all clients
        // repeat
    }

    public void sendBoard() {

    }
}
