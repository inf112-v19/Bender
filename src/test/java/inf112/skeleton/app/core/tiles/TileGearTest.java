package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import org.junit.Assert.*;
import org.junit.Test;

public class TileGearTest {
    private TileGear tile;

    @Test
    public void getAngleTest() {
        Direction dir = Direction.NORTH;

        this.tile = new TileGear(null, null, dir);

        assert this.tile.getAngle() == dir;
    }

    @Test
    public void rotateTest() {
        // TODO
        assert true;
    }

    @Test
    public void execTest() {
        // TODO
        assert true;
    }
}
