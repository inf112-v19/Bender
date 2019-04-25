package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileBlackhole extends Tile {

    public TileBlackhole(IRobot robot, Flag flag, boolean[] walls) { super(robot, flag, walls); }

    @Override
    public TileBlackhole copy() {
        return new TileBlackhole(this.getRobot(), (Flag) this.getFlag(), super.walls);
    }
}
