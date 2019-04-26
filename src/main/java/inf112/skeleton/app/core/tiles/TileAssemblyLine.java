package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLine extends Tile {

    private boolean isExpress;
    private Direction lineDir;

    public TileAssemblyLine(IRobot robot, IFlag flag, boolean[] walls, boolean isExpress, Direction dir) {
        super(robot, flag, walls);
        this.isExpress = isExpress;
        this.lineDir = dir;
    }

    public boolean getExpress() { return this.isExpress; }
    public void switchExpress() { this.isExpress = !this.isExpress; }


    public Direction getDirection() { return this.lineDir; }
    public void setDirection(Direction dir) { this.lineDir = dir; }

    @Override
    public TileAssemblyLine copy() {
        IRobot robot_copy = super.robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileAssemblyLine(robot_copy, flag_copy, super.walls, this.getExpress(), this.getDirection());
    }
}
