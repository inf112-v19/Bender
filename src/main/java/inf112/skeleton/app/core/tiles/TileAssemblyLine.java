package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLine extends Tile {

    private boolean isExpress;
    private Direction lineDir;

    public TileAssemblyLine(IRobot robot, Flag flag, boolean[] walls, boolean isExpress, Direction dir) {
        super(robot, flag, walls);
        this.isExpress = isExpress;
        this.lineDir = dir;
    }

    public boolean getExpress() { return this.isExpress; }
    public void switchExpress() { this.isExpress = !this.isExpress; }


    public Direction getDirection() { return this.lineDir; }
    public void setDirection(Direction dir) { this.lineDir = dir; }
}
