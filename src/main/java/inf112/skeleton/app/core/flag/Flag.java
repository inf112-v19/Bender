package inf112.skeleton.app.core.flag;

public class Flag implements IFlag {
    private int ordinal;
    // Color color;

    public Flag(int ord) { this.ordinal = ord; }

    public int getOrdinal() { return this.ordinal; }

    public void setOrdinal(int ord) { this.ordinal = ord; }

    public int compareTo(IFlag that) {
        return this.getOrdinal() - that.getOrdinal();
    }

    public Flag copy() {
        return new Flag(ordinal);
    }
}
