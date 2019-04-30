package inf112.skeleton.app.server;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.player.IPlayer;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

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

    void updateCards(HashMap hashMap);
}
