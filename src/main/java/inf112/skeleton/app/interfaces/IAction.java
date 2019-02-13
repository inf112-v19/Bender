package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.core.board.IBoard;

/**
 * Handler for server responses. #API implementation
 * should call these methods when it receives a response
 * from the server.
 */
public interface IAction {
    void handleERROR(String message);
    void handleWARNING(String message);
    void handleINFO(String message);
    void handleBOARD(IBoard board);
}
