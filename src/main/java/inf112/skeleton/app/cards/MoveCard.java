package inf112.skeleton.app.cards;

public class MoveCard extends ProgramCard {

    enum Direction { FORWARDS, BACKWARDS }

    private final int ammount;
    private final Direction dir;

    public MoveCard(int priority, Direction dir, int ammount) {
        super(priority);
        this.dir = dir;
        this.ammount = ammount;
    }

    public void execute() {
        // TODO: implement
    }
}
