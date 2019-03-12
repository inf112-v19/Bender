package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileBlackhole extends Tile {

    public TileBlackhole(IRobot robot, Flag flag) { super(robot, flag); }

    /**
     * Method for swallowing the robot into the black hole
     */
    public void removeRobot() {
        IRobot robot = super.getRobot();

        super.setRobot(null);
    }

    @Override
    public void exec() { this.removeRobot(); }
}
