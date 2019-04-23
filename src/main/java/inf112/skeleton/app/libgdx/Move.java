package inf112.skeleton.app.libgdx;

import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;

public class Move {

    private IRobot robot;
    private Position start;
    private Position end;

    public Move(IRobot robot, Position start, Position end) {
        this.robot = robot;
        this.start = start;
        this.end = end;
    }

    public Position getFrom() {
        return this.start;
    }

    public Position getEnd() {
        return this.end;
    }

    public IRobot getRobot() {
        return this.robot;
    }
}
