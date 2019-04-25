package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileBlackhole extends Tile {

    public TileBlackhole(IRobot robot, IFlag flag, boolean[] walls) { super(robot, flag, walls); }

    @Override
    public TileBlackhole copy() {
        IRobot robot_copy = robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileBlackhole(robot_copy, flag_copy, super.walls);
    }
}
