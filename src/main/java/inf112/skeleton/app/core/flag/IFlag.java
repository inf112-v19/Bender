package inf112.skeleton.app.core.flag;

/**
 * IFlag extends Comparable just so IFlag can be compared in the tests
 */
public interface IFlag extends Comparable<IFlag> {
    int getOrdinal();

    void setOrdinal(int ord);
}
