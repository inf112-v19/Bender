package inf112.skeleton.app.tiles;

import inf112.skeleton.app.interfaces.IRobot;
import inf112.skeleton.app.enums.Dir;

public class TileAssemblyLine extends Tile {
    private int strength;
    private Dir lineDir;

    public TileAssemblyLine(IRobot robot, Flag flag, int strength, Dir dir) {
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
        super.moveRobot(lineDir);
    }

    @Override
    public void exec() { this.moveRobot(); }
}
