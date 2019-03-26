package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileGear extends Tile {
    private DirectionChange angle;

    public TileGear(IRobot robot, Flag flag, DirectionChange angle) {
        super(robot, flag);
        this.angle = angle;
    }

    public DirectionChange getAngle() { return this.angle; }
}
