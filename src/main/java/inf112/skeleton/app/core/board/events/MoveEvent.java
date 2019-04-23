package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;

public class MoveEvent implements Event {

    private Position startPosition;
    private Position endPosition;
    private IRobot robot;

    public MoveEvent(IRobot robot, Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.robot = robot;
    }

    @Override
    public void apply(IBoard board) {
        board.moveRobotToNewTile(startPosition, endPosition);
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public IRobot getRobot() {
        return robot;
    }
}
