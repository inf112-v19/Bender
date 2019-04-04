package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import static org.junit.Assert.*;

import inf112.skeleton.app.core.enums.DirectionChange;
import org.junit.Test;

public class TileGearTest {
    private TileGear tile;

    @Test
    public void setAngleOnRobotGivesSameBackWhenGetAngleTest() {
        boolean[] walls = new boolean[4];
        DirectionChange dir = DirectionChange.RIGHT;

        this.tile = new TileGear(null, null, walls, dir);

        assertEquals(this.tile.getAngle(), dir);
    }
}
