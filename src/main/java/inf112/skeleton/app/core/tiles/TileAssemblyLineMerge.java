package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLineMerge extends TileAssemblyLine {
    public TileAssemblyLineMerge(IRobot robot, IFlag flag, boolean[] walls, boolean isExpress, Direction dir) {
        super(robot, flag, walls, isExpress, dir);
    }

    @Override
    public TileAssemblyLineMerge copy() {
        IRobot robot_copy = robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileAssemblyLineMerge(robot_copy, flag_copy, super.walls, this.getExpress(), this.getDirection());
    }
}
