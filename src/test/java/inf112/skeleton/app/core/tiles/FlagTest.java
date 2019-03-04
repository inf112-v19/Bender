package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.flag.Flag;
import org.junit.Test;

public class FlagTest {
    private Flag flag;

    @Test
    public void getOrdinalTest() {
        int ord = 0;
        this.flag = new Flag(ord);

        assert this.flag.getOrdinal() == ord;
    }

    @Test
    public void setOrdinalTest() {
        this.flag = new Flag(0);

        int ord = 5;
        this.flag.setOrdinal(ord);

        assert this.flag.getOrdinal() == ord;
    }
}
