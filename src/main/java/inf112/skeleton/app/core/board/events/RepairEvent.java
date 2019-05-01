package inf112.skeleton.app.core.board.events;

import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.robot.IRobot;

public class RepairEvent implements Event {
    private IRobot robot;
    private int level;

    public RepairEvent(IRobot robot, int level) {
        this.robot = robot;
        this.level = level;
    }

    private int getRepairLevel() {
        return this.level;
    }

    @Override
    public void apply(IBoard board) {
        IRobot rob = board.getRobot(robot);
        rob.giveEnergy(this.getRepairLevel());
    }
}
