package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.board.Position;

public class LaserEvent implements Event {

    private Position startPosition;
    private Position endPosition;

    public LaserEvent(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public void apply(IBoard board) {

    }
}
