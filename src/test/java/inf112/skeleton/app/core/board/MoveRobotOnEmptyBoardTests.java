package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.*;

import org.junit.*;
import static org.junit.Assert.assertTrue;

public class MoveRobotOnEmptyBoardTests {

    private IRobot robot;
    private IBoard board;

    @Before
    public void beforeEach() {
        this.robot = new Robot(Direction.NORTH);
        this.board = new Board("empty", 10, 10);
        this.board.addRobot(robot, new Position(5, 5));
    }

    @Test
    public void moveOne() {
        board.moveRobot(robot, Direction.NORTH, 1);
        assertTrue(!board.getTile(new Position(5, 5)).hasRobot());
        assertTrue(board.getTile(new Position(5, 6)).hasRobot());
    }

    @Test
    public void moveTwo() {
        board.moveRobot(robot, Direction.NORTH, 2);
        assertTrue(!board.getTile(new Position(5, 5)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 6)).hasRobot());
        assertTrue(board.getTile(new Position(5, 7)).hasRobot());
    }

    @Test
    public void tryToMoveOutsideBoard() {
        board.moveRobot(robot, Direction.NORTH, 100);
        assertTrue(!board.getTile(new Position(5, 5)).hasRobot());
        assertTrue(board.getTile(new Position(5, 9)).hasRobot());
    }

    @Test
    public void moveIntoOtherRobot() {
        board.addRobot(new Robot(Direction.NORTH), new Position(5, 6));
        board.moveRobot(robot, Direction.NORTH, 3);
        assertTrue(board.getTile(new Position(5, 9)).hasRobot());
        assertTrue(board.getTile(new Position(5, 8)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 7)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 6)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 5)).hasRobot());
    }
}
