package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import static org.junit.Assert.*;
import org.junit.Test;

public class TileTest {
    private Tile tile;

    @Test
    public void getRobotTest() {
        IRobot robot = new Robot(Direction.NORTH);
        this.tile = new Tile(robot, null);

        assertEquals(this.tile.getRobot().compareTo(robot), 0);
    }

    @Test
    public void setRobotTest() {
        this.tile = new Tile(new Robot(Direction.NORTH), null);

        IRobot robot = new Robot(Direction.EAST);
        this.tile.setRobot(robot);

        assertEquals(this.tile.getRobot().compareTo(robot), 0);
    }

    @Test
    public void hasFlagTest() {
        IFlag flag = new Flag(0);
        this.tile = new Tile(null, flag);

        assertTrue(this.tile.hasFlag());
    }

    @Test
    public void getFlagTest() {
        Flag flag = new Flag(0);
        this.tile = new Tile(null, flag);

        assertEquals(this.tile.getFlag().compareTo(flag), 0);
    }

    @Test
    public void execTest() {
        // TODO: implement
    }
}
