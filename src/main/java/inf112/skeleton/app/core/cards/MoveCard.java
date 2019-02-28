package inf112.skeleton.app.core.cards;

public class MoveCard extends ProgramCard {

    public enum MoveDirection { FORWARDS, BACKWARDS }

    private final int ammount;
    private final MoveDirection dir;

    public MoveCard(int priority, MoveDirection dir, int ammount) {
        super(priority);
        this.dir = dir;
        this.ammount = ammount;
    }
}

