package inf112.skeleton.app.core.laser;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.LaserEvent;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.lazer.Lazer;
import inf112.skeleton.app.core.robot.Robot;
import org.junit.Before;
import org.junit.Test;

import static inf112.skeleton.app.core.enums.Direction.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class laserTests {

    private Robot robot;
    private Robot secondRobot;
    private Board board;

    @Before
    public void beforeEach() {
        this.robot = new Robot(SOUTH);
        this.secondRobot = new Robot(Direction.SOUTH);
        this.board = new Board("empty", 12, 12);
        this.board.addRobot(robot, new Position(5, 5));
        this.board.addRobot(secondRobot, new Position(5, 9));
        Event shouldBeCoordinates = new LaserEvent(new Position(5,5), new Position(5,9));
    }

    @Test
    public void checkEnergyAndPosisionWhenGivingRobotsAndBoard() throws Exception {

        Event getCoordinates = new LaserEvent(null, null);
        assertEquals(8, this.robot.getEnergy());
        assertEquals(8, this.secondRobot.getEnergy());
        getCoordinates = Lazer.shootLazer(robot, board);
        assertEquals(7, secondRobot.getEnergy());
        assertEquals(SOUTH, this.board.getRobotDirection(robot));
        getCoordinates = Lazer.shootLazer(robot, board);
        assertEquals(6, secondRobot.getEnergy());
    }

    @Test
    public void checkEnergyWhenTUrningRobotAndShootLaserTwice() throws Exception {
        robot.setDirection(WEST);
        robot.setDirection(SOUTH);
        Lazer.shootLazer(robot, board);
        assertEquals(7, secondRobot.getEnergy());
        Lazer.shootLazer(robot, board);
        assertEquals(6, secondRobot.getEnergy());
    }
}
