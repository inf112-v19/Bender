package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLineSplit extends TileAssemblyLine {
    public TileAssemblyLineSplit(IRobot robot, IFlag flag, boolean[] walls, boolean isExpress, Direction dir) {
        super(robot, flag, walls, isExpress, dir);
    }

    @Override
    public TileAssemblyLineSplit copy() {
        IRobot robot_copy = robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileAssemblyLineSplit(robot_copy, flag_copy, super.walls, this.getExpress(), this.getDirection());
    }
}
