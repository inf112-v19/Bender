package inf112.skeleton.app.tiles;

import inf112.skeleton.app.enums.Dir;
import inf112.skeleton.app.interfaces.IRobot;

public class Tile {
    private static Tile[] neighbors = (Tile[]) (new Object[4]);
    private IRobot robot;
    private Flag flag;

    public Tile(IRobot robot, Flag flag) {
        this.robot = robot;
        this.flag = flag;
    }

    /**
     * Method for checking if robot can mode in a direction
     *
     * @param dir The direction to check
     *
     * @return The check result
     */
    boolean canMove(Dir dir) {
        return false;
    }

    IRobot getRobot() {
        return this.robot;
    }
    void setRobot(IRobot robot) {
        this.robot = robot;
    }

    /**
     * Method for checking if tile has flag
     *
     * @return Result of check*/
    boolean hasFlag() {
        return !(this.flag == null);
    }

    /**
     * Method for getting the the current flag
     *
     * @return The flag*/
    Flag getFlag() {
        if(this.hasFlag())
            return this.flag;

        return null;
    }

    /**
     * Method for moving robot to neighbor
     *
     * @param dir The direction to move the robot in
     *
     * @throws Error if the direction is invalid
     */
    public void moveRobot(Dir dir) throws Error {
        if(!this.canMove(dir)) return;

        int dirInt = -1;
        switch (dir) {
            case NONE: break;
            case NORTH:
                dirInt = 0;
                break;
            case EAST:
                dirInt = 1;
                break;
            case SOUTH:
                dirInt = 2;
                break;
            case WEST:
                dirInt = 3;
                break;
            default:
                throw new Error();
        }

        // TODO: Add setTile method to robot
        //this.robot.setTile(this.neighbors[dirInt]);

        this.neighbors[dirInt].setRobot(this.robot);
        this.robot = null;
    }

    public void exec() { this.moveRobot(Dir.NONE); }
}
