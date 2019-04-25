package inf112.skeleton.app.server;

import com.google.gson.Gson;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.core.position.Position;
import org.java_websocket.WebSocket;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

public class GameRoom {

    private static boolean selectionDone;
    private String roomId;
    private Board board;
    private static HashMap<Player, WebSocket> connections;
    private static HashMap<WebSocket, ArrayDeque<IProgramCard>> collectiveCards;

    private Gson json = new Gson();

    public GameRoom(String roomId) {
        collectiveCards = new HashMap<>();
        connections = new HashMap<>();
        selectionDone = false;
        this.roomId = roomId;
        board = new Board("empty", 10, 10);
    }


    public HashMap<Player, ArrayDeque<IProgramCard>> getResponseMap() {
        HashMap<Player, ArrayDeque<IProgramCard>> finalMap = new HashMap<>();
        for (WebSocket key : collectiveCards.keySet())
            finalMap.put(getPlayer(key), collectiveCards.get(key));
        return finalMap;
    }

    private Player getPlayer(WebSocket conn) {
        for (Player player : connections.keySet())
            if (connections.get(player).equals(conn))
                return player;
        return null;
    }

    public boolean getStatus() {
        return selectionDone;
    }

    public static HashMap<WebSocket, ArrayDeque<IProgramCard>> getCollectiveCards() {
        return collectiveCards;
    }

    public String getRoomId() {
        return roomId;
    }

    public void startGame(List<WebSocket> sockets) {
        for (WebSocket socket : sockets) {
            Player player = new Player(socket.getRemoteSocketAddress().toString());
            connections.put(player, socket);
            socket.send("RESPONSE" + json.toJson(board));
            socket.send("PLAYER " + json.toJson(player));
        }
        // deal cards to players
    }

    // client sends cards to server.
    //server sends list of players and their chosen cards to the client
    //client executes animation for each player
    //board is updated at server
    //repeat
    public void addChosenCards(WebSocket web, ArrayDeque<IProgramCard> cards) {
        connections.put(new Player(web.getRemoteSocketAddress().toString()), web); // temporary, until startGame(); is in use
        collectiveCards.put(web, cards);
        System.out.println("collective cards keyset size: " + collectiveCards.keySet().size());
        System.out.println("total connections : " + connections.values().size());
        if (collectiveCards.keySet().size() == connections.values().size()) {
            System.out.println("this works");
            selectionDone = true;
        }
    }

    public void updateBoard(Board board) {
        this.board = board;
    }
}
