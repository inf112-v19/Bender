package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.enums.DirectionChange;


// TODO : implement

public class RotateEvent implements Event {

    private DirectionChange dirChange;

    public RotateEvent(DirectionChange dirChange) {
        this.dirChange = dirChange;
    }

    @Override
    public void apply(IBoard board) {

    }
}
