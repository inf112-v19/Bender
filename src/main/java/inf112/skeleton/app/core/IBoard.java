package inf112.skeleton.app.core;

import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.interfaces.IRobot;
import inf112.skeleton.app.interfaces.ITile;

public interface IBoard {

    /**
     * Get the tile at (x, y)
     * @param x
     * @param y
     * @return
     */
    ITile getTile(int x, int y);    // Method for getting a tile in pos (x,y)

    /**
     * Get the tile at (position.x, position.y)
     * @param position
     * @return
     */
    ITile getTile(Position position);

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