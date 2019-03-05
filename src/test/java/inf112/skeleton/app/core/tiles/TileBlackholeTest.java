package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import static org.junit.Assert.*;
import org.junit.Test;

public class TileBlackholeTest {
    private TileBlackhole tile;

    @Test
    public void removeRobotTest() {
        IRobot robot = new Robot(Direction.NORTH);
        this.tile = new TileBlackhole(robot, null);

        this.tile.removeRobot();

        assertNull(this.tile.getRobot());
    }

    @Test
    public void execTest() {
        // TODO: implement
    }
}
