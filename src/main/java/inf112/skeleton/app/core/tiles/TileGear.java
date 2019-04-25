package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileGear extends Tile {
    private DirectionChange angle;

    public TileGear(IRobot robot, Flag flag, boolean[] walls, DirectionChange angle) {
        super(robot, flag, walls);
        this.angle = angle;
    }

    public DirectionChange getAngle() { return this.angle; }

    @Override
    public TileGear copy() {
        return new TileGear(this.getRobot(), (Flag) this.getFlag(), super.walls, this.getAngle());
    }
}
