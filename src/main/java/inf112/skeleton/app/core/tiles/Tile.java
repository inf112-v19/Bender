package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public class Tile implements ITile {

    private IRobot robot;
    private IFlag flag;

    public Tile() {
        this.robot = null;
        this.flag = null;
    }

    public Tile(IRobot robot, IFlag flag) {
        this.robot = robot;
        this.flag = flag;
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

    public void exec() {
        // TODO: Do rotate/push operation on robot
    }

    public boolean canEnter(Direction direction) {
        // TODO: implement
        return true;
    }
}
