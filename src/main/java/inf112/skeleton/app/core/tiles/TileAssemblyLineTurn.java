package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.flag.Flag;
import inf112.skeleton.app.core.robot.IRobot;

public class TileAssemblyLineTurn extends TileAssemblyLine {
    private DirectionChange turnDir;

    public TileAssemblyLineTurn(IRobot robot, Flag flag, boolean[] walls, boolean isExpress, Direction dir, DirectionChange turnDir) {
        super(robot, flag, walls, isExpress, dir);
        this.turnDir = turnDir;
    }

    public DirectionChange getTurnDir() { return this.turnDir; }
    public void setTurnDir(DirectionChange turnDir) { this.turnDir = turnDir; }

    @Override
    public TileAssemblyLineTurn copy() {
        return new TileAssemblyLineTurn(this.getRobot(), (Flag) this.getFlag(), super.walls, this.getExpress(), this.getDirection(), this.getTurnDir());
    }
}
