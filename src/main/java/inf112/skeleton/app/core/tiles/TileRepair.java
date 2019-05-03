package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileRepair extends Tile {
    private int level;

    public TileRepair(IRobot robot, IFlag flag, boolean[] walls, int level) {
        super(robot, flag, walls);
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    @Override
    public TileRepair copy() {
        IRobot robot_copy = super.robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileRepair(robot_copy, flag_copy, super.walls, this.getLevel());
    }
}
