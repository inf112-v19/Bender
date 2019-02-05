package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.enums.TileType;
import inf112.skeleton.app.enums.TileColor;
import inf112.skeleton.app.enums.Dir;

public interface ITile {
    TileType getType();             // Method for getting the tile type
    void setType(TileType tile);    // Method for setting the tile type

    ITile getNeighbor(Dir dir);     // Method for getting the nearest neighbor

    boolean canMove(Dir dir);       // Method for checking if robot can mode in a direction

    IRobot getRobot();              // Method for getting the current robot on the tile
    void setRobot(IRobot robot);    // Method for setting the current robot on the tile

    TileColor getColor();           // Method for getting the tile color
    void setColor(TileColor color); // Method for setting the tile color

}
