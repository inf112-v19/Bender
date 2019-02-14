package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.interfaces.ITile;

public interface IBoard extends java.io.Serializable {
    ITile getTile(int x, int y);    // Method for getting a tile in pos (x,y)

    void addTileRow();              // Method for adding a row of tiles to the board
    void addTileCol();              // Method for adding a column of tiles to the board
}
