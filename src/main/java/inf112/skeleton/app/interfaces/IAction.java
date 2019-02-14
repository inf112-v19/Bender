package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.cards.IProgramCard;

/**
 * Handler for server responses. #API implementation
 * should call these methods when it receives a response
 * from the server.
 *
 * Client object should have a non-static implementation
 * of this interface that handles the server responses
 */
public interface IAction {
    void handleERROR(String message);
    void handleWARNING(String message);
    void handleINFO(String message);
    void handleBOARD(IBoard board);
    void handleCardDraw(IProgramCard card);
}
