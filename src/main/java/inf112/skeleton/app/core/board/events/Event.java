package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;

public interface Event {

    /**
     * Executes the event on the specified board
     * @param board
     */
    void apply(IBoard board);
}
