package inf112.skeleton.app.core.player;

import inf112.skeleton.app.core.board.Board;

public interface IPlayer {
    /**
     * Get the grid from the server
     */
    Board getBoard();
}
