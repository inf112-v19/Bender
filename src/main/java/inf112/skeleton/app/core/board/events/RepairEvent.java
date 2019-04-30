package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.robot.IRobot;

public class RepairEvent implements Event {
    private IRobot robot;

    public RepairEvent(IRobot robot) {
        this.robot = robot;
    }

    @Override
    public void apply(IBoard board) {
        IRobot rob = board.getRobot(robot);
        rob.giveEnergy(1);
    }
}
