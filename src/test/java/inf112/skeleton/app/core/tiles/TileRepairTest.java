package inf112.skeleton.app.core.tiles;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TileRepairTest {
    private TileRepair tile;

    @Test
    public void getAngleOnRobotGivesSameBackWhenGetAngleTest() {
        boolean[] walls = new boolean[4];
        int level = 1;

        this.tile = new TileRepair(null, null, walls, level);

        assertEquals(this.tile.getLevel(), level);
    }

    @Test
    public void setAngleOnRobotGivesSameBackWhenGetAngleTest() {
        boolean[] walls = new boolean[4];

        this.tile = new TileRepair(null, null, walls, 1);

        int level = 2;
        this.tile.setLevel(level);

        assertEquals(this.tile.getLevel(), level);
    }
}
