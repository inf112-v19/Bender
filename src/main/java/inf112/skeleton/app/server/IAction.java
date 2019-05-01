package inf112.skeleton.app.server;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.player.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler for server responses. {@link API} implementation
 * should call these methods when it receives a response
 * from the server.
 *
 * Client object should have a non-static, internal
 * implementation of this interface that handles the server
 * responses and changes the client state.
 */
public interface IAction {

    void handleERROR(String message);

    void handleWARNING(String message);

    void handleINFO(String message);

    void handleROOM(String roomId);

    void handleCards(ArrayDeque<IProgramCard> cards);

    void handleBoard(IBoard board);

    void handleServerResponse();

    void received(boolean b);

    void updateCards(Player player, IProgramCard card);

    ArrayList<Player> getPlayers();

    boolean getRecieved();

    void addPlayer(Player player);
    void updatePlayer(Player player);

    void clearPlayerList();
}
