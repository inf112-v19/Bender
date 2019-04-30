package inf112.skeleton.app.core.laser;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.LaserEvent;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.lazer.Lazer;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class laserTests {

    private Robot robot;
    private Robot secondRobot;
    private Board board;

    @Before
    public void beforeEach() {
        this.robot = new Robot(Direction.EAST);
        this.secondRobot = new Robot(Direction.SOUTH);
        this.board = new Board("empty", 10, 10);
        this.board.addRobot(robot, new Position(5, 5));
        this.board.addRobot(secondRobot, new Position(5, 9));
        Event shouldBeCoordinates = new LaserEvent(new Position(5,5), new Position(5,9));
    }

    @Test
    public void checkEnergyAndPosisionWhenGivingRobotsAndBoard() throws Exception {

        Event getCoordinates = new LaserEvent(null, null);
        getCoordinates = Lazer.shootLazer(robot, board);
        assertEquals(8, secondRobot.getEnergy());
        assertEquals(secondRobot, this.board.getRobot(new Position(5,9)));
    }
}
