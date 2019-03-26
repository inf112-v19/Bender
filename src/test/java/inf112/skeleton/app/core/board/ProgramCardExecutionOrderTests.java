package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import org.junit.Test;

import org.junit.*;
import static org.junit.Assert.assertTrue;

public class ProgramCardExecutionOrderTests {

    private IBoard board;
    private IRobot robot1;
    private IRobot robot2;
    private IRobot robot3;

    @Before
    public void beforeEach() {
        this.robot1 = new Robot(Direction.NORTH);
        this.robot2 = new Robot(Direction.WEST);
        this.robot3 = new Robot(Direction.NORTH);
        this.board = new Board("empty", 10, 10);
        this.board.addRobot(robot1, new Position(5, 5));
        this.board.addRobot(robot2, new Position(4, 5));
        this.board.addRobot(robot3, new Position(5, 6));
    }

    @Test
    public void giveCardToThreeRobotsAndTestForCorrectPriority() {
        this.robot1.addCard(new MoveCard(10, false, 1));
        this.robot2.addCard(new MoveCard(9, false, 1));
        this.robot3.addCard(new MoveCard(8, false, 2));
        this.board.stepRobots();

        assertTrue(board.getRobot(new Position(5, 6)) == robot1);
        assertTrue(board.getRobot(new Position(3, 5)) == robot2);
        assertTrue(board.getRobot(new Position(5, 9)) == robot3);
    }
}
