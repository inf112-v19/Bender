package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.enums.TileType;
import inf112.skeleton.app.enums.TileColor;
import inf112.skeleton.app.enums.Dir;

public interface ITile {
    TileType getType();             // Method for getting the tile type
    void setType(TileType tile);    // Method for setting the tile type

    ITile getNeighbor(Dir dir);     // Method for getting the nearest neighbor

    IPlayer getPlayer();            // Method for getting the current player on the tile
    void setPlayer(IPlayer player); // Method for setting the current player on the tile

    TileColor getColor();           // Method for getting the tile color
    void setColor(TileColor color); // Method for setting the tile color
}
