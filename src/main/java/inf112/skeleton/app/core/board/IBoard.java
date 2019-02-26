package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.Position;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.interfaces.IRobot;
import inf112.skeleton.app.core.tiles.Tile;

public interface IBoard extends java.io.Serializable {

    /**
     * Get the tile at (x, y)
     * @param x
     * @param y
     * @return
     */
    Tile getTile(int x, int y);    // Method for getting a tile in pos (x,y)

    /**
     * Get the tile at (position.x, position.y)
     * @param position
     * @return
     */
    Tile getTile(Position position);

    /**
     * Execute a program card on a robot
     * @param robot
     * @param card
     */
    void moveRobot(IRobot robot, IProgramCard card);

    /**
     * Move a robot one square in a direction
     * @param robot
     * @param dir
     */
    void moveRobot(IRobot robot, Direction dir);
}
