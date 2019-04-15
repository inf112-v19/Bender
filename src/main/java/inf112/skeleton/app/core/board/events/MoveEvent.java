package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.position.Position;

public class MoveEvent implements Event {

    private Position startPosition;
    private Position endPosition;

    public MoveEvent(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public void apply(IBoard board) {
        // TODO : implement
    }
}
