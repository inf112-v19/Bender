package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLineMerge extends TileAssemblyLine {
    public TileAssemblyLineMerge(IRobot robot, Flag flag, boolean[] walls, boolean isExpress, Direction dir) {
        super(robot, flag, walls, isExpress, dir);
    }

    @Override
    public TileAssemblyLineMerge copy() {
        return new TileAssemblyLineMerge(this.getRobot(), (Flag) this.getFlag(), super.walls, this.getExpress(), this.getDirection());
    }
}
