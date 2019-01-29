package inf112.skeleton.app.cards;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RotateCard extends ProgramCard {

    enum Direction { RIGHT, LEFT, UTURN }

    private final Direction dir;

    public RotateCard(int priority, Direction dir) {
        super(priority);
        this.dir = dir;
    }

    public void execute() {
        throw new NotImplementedException();
    }
}
