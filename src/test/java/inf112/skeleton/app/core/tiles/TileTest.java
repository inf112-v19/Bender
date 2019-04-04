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
    public void putNewRobotOnTileandGetRobotBackAndCompare() {
        IRobot robot = new Robot(Direction.NORTH);
        boolean[] walls = new boolean[4];
        this.tile = new Tile(robot, null, walls);

        assertEquals(this.tile.getRobot().compareTo(robot), 0);
    }

    @Test
    public void setRobotOnTileAndCompareWithRobot() {
        boolean[] walls = new boolean[4];
        this.tile = new Tile(new Robot(Direction.NORTH), null, walls);

        IRobot robot = new Robot(Direction.EAST);
        this.tile.setRobot(robot);

        assertEquals(this.tile.getRobot().compareTo(robot), 0);
    }

    @Test
    public void setFlagOnTileAndTestIfItHasFlag() {
        boolean[] walls = new boolean[4];
        IFlag flag = new Flag(0);
        this.tile = new Tile(null, flag, walls);

        assertTrue(this.tile.hasFlag());
    }

    @Test
    public void makeTileWithFlagAndGetFlagAndCompare() {
        boolean[] walls = new boolean[4];
        Flag flag = new Flag(0);
        this.tile = new Tile(null, flag, walls);

        assertEquals(this.tile.getFlag().compareTo(flag), 0);
    }
}
