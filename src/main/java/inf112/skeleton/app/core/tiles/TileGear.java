package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileGear extends Tile {
    private Direction angle;

    public TileGear(IRobot robot, Flag flag, Direction angle) {
        super(robot, flag);
        this.angle = angle;
    }

    public Direction getAngle() { return this.angle; }


    /**
     * Method for rotating the robot on the current tile
     */
    public void rotate() {
        // TODO: add rotate method to Robot
        super.getRobot();//.rotate(this.angle);
    }

    @Override
    public void exec() { this.rotate(); }
}
