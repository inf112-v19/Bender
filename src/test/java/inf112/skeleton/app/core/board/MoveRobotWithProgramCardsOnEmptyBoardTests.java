package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.RotateCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import org.junit.*;
import static org.junit.Assert.assertTrue;

public class MoveRobotWithProgramCardsOnEmptyBoardTests {

    private IBoard board;
    private IRobot robot;

    @Before
    public void beforeEach() {
        this.robot = new Robot(Direction.NORTH);
        this.board = new Board("empty", 10, 10);
        this.board.addRobot(robot, new Position(5, 5));
    }

    @Test
    public void rotateRightTest() {
        IProgramCard card = new RotateCard(0, DirectionChange.RIGHT);
        Direction currentDir = robot.getDirection();
        board.moveRobot(robot, card);
        assertTrue(currentDir.getRight() == robot.getDirection());
    }

    @Test
    public void rotateLeftTest() {
        IProgramCard card = new RotateCard(0, DirectionChange.LEFT);
        Direction currentDir = robot.getDirection();
        board.moveRobot(robot, card);
        assertTrue(currentDir.getLeft() == robot.getDirection());
    }

    @Test
    public void moveOneForwardTest() {
        IProgramCard card = new MoveCard(0, false, 1);
        board.moveRobot(robot, card);
        assertTrue(board.hasRobot(new Position(5, 6)));
        assertTrue(!board.hasRobot(new Position(5, 5)));
    }

    @Test
    public void moveThreeForwardTest() {
        IProgramCard card  = new MoveCard(0, false, 3);
        board.moveRobot(robot, card);
        assertTrue(board.hasRobot(new Position(5, 8)));
        assertTrue(!board.hasRobot(new Position(5, 5)));
        assertTrue(!board.hasRobot(new Position(5, 6)));
        assertTrue(!board.hasRobot(new Position(5, 7)));
    }

    @Test
    public void moveBackwardsTwoTest() {
        IProgramCard card = new MoveCard(0, true, 2);
        board.moveRobot(robot, card);
        assertTrue(board.hasRobot(new Position(5, 3)));
        assertTrue(!board.hasRobot(new Position(5, 5)));
        assertTrue(!board.hasRobot(new Position(5, 4)));
    }

    @Test
    public void moveIntoOtherRobot() {
        board.addRobot(new Robot(Direction.NORTH), new Position(5, 6));
        IProgramCard card = new MoveCard(0, false, 3);
        board.moveRobot(robot, card);
        assertTrue(board.getTile(new Position(5, 9)).hasRobot());
        assertTrue(board.getTile(new Position(5, 8)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 7)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 6)).hasRobot());
        assertTrue(!board.getTile(new Position(5, 5)).hasRobot());
    }
}
