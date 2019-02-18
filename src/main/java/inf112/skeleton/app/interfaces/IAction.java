package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.IBoard;

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
    void handleBOARD(IBoard board);
    void handleCardDraw(IProgramCard card);
}
