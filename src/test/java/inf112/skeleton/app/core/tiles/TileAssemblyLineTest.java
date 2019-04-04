package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import static org.junit.Assert.*;
import org.junit.Test;

public class TileAssemblyLineTest {

    private TileAssemblyLine tile;

    @Test
    public void setStrengthGetBackAndCompare() {
        boolean isExpress = true;
        boolean[] walls = new boolean[4];
        this.tile = new TileAssemblyLine(null, null, walls, isExpress, Direction.NORTH);

        assertEquals(this.tile.getExpress(), isExpress);
    }

    @Test
    public void makeNewTileAndChangeStrengthAndCompare() {
        boolean[] walls = new boolean[4];
        this.tile = new TileAssemblyLine(null, null, walls, true, Direction.NORTH);

        boolean state = this.tile.getExpress();
        this.tile.switchExpress();

        assertEquals(this.tile.getExpress(), !state);
    }
}
