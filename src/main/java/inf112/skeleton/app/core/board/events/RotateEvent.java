package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.robot.IRobot;

// TODO : implement

public class RotateEvent implements Event {

    private DirectionChange dirChange;
    private IRobot robot;

    public RotateEvent(IRobot robot, DirectionChange dirChange) {
        this.robot = robot;
        this.dirChange = dirChange;
    }

    @Override
    public void apply(IBoard board) {
        board.rotateRobot(robot, dirChange);
    }
}
