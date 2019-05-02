package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.core.robot.IRobot;

public class LaserEvent implements Event {

    private Position startPosition;
    private Position endPosition;

    public LaserEvent(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Position getEndPosition (Event laserEvent) {
        return this.endPosition;
    }

    public Position getStartPosition (Event laserEvent) {
        return this.startPosition;
    }

    @Override
    public void apply(IBoard board) {
        IRobot robot = board.getRobot(endPosition);
        if(robot != null) {
            // NOTE: should we implement different laser types? Meaning, lasers that take more than 1 damage?
            robot.takeEnergy(1);
        }
    }
}
