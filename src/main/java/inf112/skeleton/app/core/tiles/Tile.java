package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class Tile implements ITile {

    private IRobot robot;
    private IFlag flag;
    private boolean[] walls = new boolean[4];

    public Tile() {
        this.robot = null;
        this.flag = null;
    }

    public Tile(IRobot robot, IFlag flag, boolean[] walls) {
        this.robot = robot;
        this.flag = flag;

        if(walls.length == 4)
            this.walls = walls;
        else
            throw new IllegalArgumentException("Wall array must be of length 4");
    }

    public boolean hasRobot() {
        return robot != null;
    }

    public IRobot getRobot() {
        return this.robot;
    }

    public void setRobot(IRobot robot) {
        this.robot = robot;
    }

    public boolean hasFlag() {
        return !(this.flag == null);
    }

    public IFlag getFlag() {
        if(this.hasFlag()) return this.flag;
        return null;
    }

    public boolean canEnter(Direction dir) {
        switch (dir) {
            case NORTH:
                return !this.walls[0];

            case EAST:
                return !this.walls[1];

            case SOUTH:
                return !this.walls[2];

            case WEST:
                return !this.walls[3];

            default:
                return false;
        }
    }
}
