package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLineTurn extends TileAssemblyLine {
    private DirectionChange turnDir;

    public TileAssemblyLineTurn(IRobot robot, IFlag flag, boolean[] walls, boolean isExpress, Direction dir, DirectionChange turnDir) {
        super(robot, flag, walls, isExpress, dir);
        this.turnDir = turnDir;
    }

    public DirectionChange getTurnDir() { return this.turnDir; }
    public void setTurnDir(DirectionChange turnDir) { this.turnDir = turnDir; }

    @Override
    public TileAssemblyLineTurn copy() {
        IRobot robot_copy = robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileAssemblyLineTurn(robot_copy, flag_copy, super.walls, this.getExpress(), this.getDirection(), this.getTurnDir());
    }
}
