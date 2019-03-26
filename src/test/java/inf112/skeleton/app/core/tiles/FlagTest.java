package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.flag.Flag;
import static org.junit.Assert.*;
import org.junit.Test;

public class FlagTest {
    private Flag flag;

    @Test
    public void setOrdinalToFlagAndGetSameOrdinalBackTest() {
        int ord = 0;
        this.flag = new Flag(ord);

        assertEquals(this.flag.getOrdinal(), ord);
    }

    @Test
    public void changeOrdinalOfFlagAndSetNewOrdinalAndGetSameBackTest() {
        this.flag = new Flag(0);

        int ord = 5;
        this.flag.setOrdinal(ord);

        assertEquals(this.flag.getOrdinal(), ord);
    }
}
