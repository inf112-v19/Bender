package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import static org.junit.Assert.*;
import org.junit.Test;

public class TileAssemblyLineTest {

    private TileAssemblyLine tile;

    @Test
    public void getStrengthTest() {
        int strength = 5;
        this.tile = new TileAssemblyLine(null, null, strength, Direction.NORTH);

        assertEquals(this.tile.getStrength(), strength);
    }

    @Test
    public void setStrengthTest() {
        this.tile = new TileAssemblyLine(null, null, 0, Direction.NORTH);

        int strength = 10;
        this.tile.setStrength(strength);

        assertEquals(this.tile.getStrength(), strength);
    }

    @Test
    public void moveRobotTest() {
        // TODO: implement
    }

    @Test
    public void execTest() {
        // TODO: implement
    }
}
