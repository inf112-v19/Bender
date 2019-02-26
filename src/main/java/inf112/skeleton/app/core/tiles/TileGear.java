package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.robot.IRobot;

public class TileGear extends Tile {
    private int angle;

    public TileGear(IRobot robot, Flag flag, int angle) {
        super(robot, flag);
        this.angle = angle;
    }

    public int getAngle() { return this.angle; }


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
