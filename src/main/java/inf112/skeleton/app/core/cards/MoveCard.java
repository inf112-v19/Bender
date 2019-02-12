package inf112.skeleton.app.core.cards;

public class MoveCard extends ProgramCard {

    public enum Direction { FORWARDS, BACKWARDS }

    private final int ammount;
    private final Direction dir;

    public MoveCard(int priority, Direction dir, int ammount) {
        super(priority);
        this.dir = dir;
        this.ammount = ammount;
    }
}
