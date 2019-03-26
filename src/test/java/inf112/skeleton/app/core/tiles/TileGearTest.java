package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import static org.junit.Assert.*;
import org.junit.Test;

public class TileGearTest {
    private TileGear tile;

    @Test
    public void setAngleOnRobotGivesSameBackWhenGetAngleTest() {
        Direction dir = Direction.NORTH;

        this.tile = new TileGear(null, null, dir);

        assertEquals(this.tile.getAngle(), dir);
    }
}
