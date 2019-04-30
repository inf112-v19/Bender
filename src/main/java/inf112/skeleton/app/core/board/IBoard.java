package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.ITile;

import java.util.List;
import java.util.Queue;

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
    Queue<List<Event>> moveRobot(IRobot robot, IProgramCard card);

    /**
     *
     * @param robot
     * @param dir
     * @param amount
     * @return true if the robot moved
     */
    Queue<List<Event>> moveRobot(IRobot robot, Direction dir, int amount);

    /**
     * Add robot at position if legal
     *
     * @throws IllegalArgumentException if the position if illegal
     * @param robot
     * @param position
     */
    void addRobot(IRobot robot, Position position);

    void addRobot(IRobot robot);

    /**
     * @param pos
     * @return true if there is a robot at pos
     */
    boolean hasRobot(Position pos);

    /**
     * @param pos
     * @return robot object if robot at position, otherwise null
     */
    IRobot getRobot(Position pos);

    /**
     * Takes the top card from each robot and moves them in
     * prioritized order
     */
    Queue<List<Event>> stepProgramCards();

    /**
     *
     * @param robot
     * @return
     */
    Position getRobotPosition(IRobot robot);

    Event moveRobotToNewTile(Position from, Position to);

    IRobot getRobot(IRobot robot);

    Direction getRobotDirection(IRobot robot);

    void rotateRobot(IRobot robot, DirectionChange change);

    int numberOfRobots();

    boolean containsRobot(IRobot robot);
}
