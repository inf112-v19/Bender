package inf112.skeleton.app.core.cards;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;

public class  RotateCard extends ProgramCard {

    private final DirectionChange dir;

    public RotateCard(int priority, DirectionChange dir) {
        super(priority);
        this.dir = dir;
    }

    public Direction getDir(Direction currentDirection) {
        switch (this.dir) {
            case RIGHT: return currentDirection.getRight();
            case LEFT: return currentDirection.getLeft();
            case UTURN: return currentDirection.getOpposite();
            default: return null;
        }
    }
    public DirectionChange getCurrentDirection() {
        return dir;
    }
}
