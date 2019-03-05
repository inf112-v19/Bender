package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.ITile;

public interface IBoard extends java.io.Serializable {

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
     *
     * @param robot
     * @param dir
     * @param amount
     * @return true if the robot moved
     */
    boolean moveRobot(IRobot robot, Direction dir, int amount);

    /**
     * Add robot at position if legal
     *
     * @throws IllegalArgumentException if the position if illegal
     * @param robot
     * @param position
     */
    void addRobot(IRobot robot, Position position);

    /**
     * The board chooses position
     * @param robot
     */
    void addRobot(IRobot robot);
}
