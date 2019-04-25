package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileGear extends Tile {
    private DirectionChange angle;

    public TileGear(IRobot robot, IFlag flag, boolean[] walls, DirectionChange angle) {
        super(robot, flag, walls);
        this.angle = angle;
    }

    public DirectionChange getAngle() { return this.angle; }

    @Override
    public TileGear copy() {
        IRobot robot_copy = robot == null ? null : robot.copy();
        IFlag flag_copy = flag == null ? null : flag.copy();
        return new TileGear(robot_copy, flag_copy, super.walls, this.getAngle());
    }
}
