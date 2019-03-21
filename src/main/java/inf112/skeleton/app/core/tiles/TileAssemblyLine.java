package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLine extends Tile {
    private int strength;
    private Direction lineDir;

    public TileAssemblyLine(IRobot robot, Flag flag, int strength, Direction dir) {
        super(robot, flag);
        this.strength = strength;
        this.lineDir = dir;
    }

    public int getStrength() { return this.strength; }
    public void setStrength(int strength) { this.strength = strength; }


    /**
     * Method for moving the robot from the current tile to a neighbor
     */
    public void moveRobot() {
        //
    }
    public Direction getDirection() {
        return lineDir;
    }

    @Override
    public void exec() { this.moveRobot(); }
}
