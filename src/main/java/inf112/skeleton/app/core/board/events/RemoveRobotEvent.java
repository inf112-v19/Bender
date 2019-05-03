package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.robot.IRobot;

public class RemoveRobotEvent implements Event {

    private IRobot robot;

    public RemoveRobotEvent(IRobot robot) {
        this.robot = robot;
    }

    @Override
    public void apply(IBoard board) {
        IRobot realRobot = board.getRobot(robot);
        // tODO : remove robot
    }
}
