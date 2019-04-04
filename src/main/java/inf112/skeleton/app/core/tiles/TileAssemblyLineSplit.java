package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLineSplit extends TileAssemblyLine {
    public TileAssemblyLineSplit(IRobot robot, Flag flag, boolean[] walls, boolean isExpress, Direction dir) {
        super(robot, flag, walls, isExpress, dir);
    }
}
