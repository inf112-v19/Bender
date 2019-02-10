package inf112.skeleton.app.core.cards;

public class RotateCard extends ProgramCard {

    enum Direction { RIGHT, LEFT, UTURN }

    private final Direction dir;

    public RotateCard(int priority, Direction dir) {
        super(priority);
        this.dir = dir;
    }
}
