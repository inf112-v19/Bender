package inf112.skeleton.app.tiles;

import inf112.skeleton.app.interfaces.IRobot;

public class TileBlackhole extends Tile {

    public TileBlackhole(IRobot robot, Flag flag) {
        super(robot, flag);
    }

    /**
     * Method for swallowing the robot into the black hole
     */
    public void removeRobot() {
        IRobot robot = super.getRobot();

        // TODO: Add setTile method to robot
        //robot.setTile(null);
        super.setRobot(null);
    }

    @Override
    public void exec() { this.removeRobot(); }
}
