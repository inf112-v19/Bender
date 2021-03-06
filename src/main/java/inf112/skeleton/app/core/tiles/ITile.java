package inf112.skeleton.app.core.tiles;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;
import inf112.skeleton.app.core.robot.IRobot;

public interface ITile {

    IRobot getRobot();

    void setRobot(IRobot robot);

    boolean hasRobot();

   /**
    * Method for checking if tile has flag
    *
    * @return Result of check
    */
    boolean hasFlag();

   /**
    * Method for getting the the current flag
    *
    * @return The flag
    */
    IFlag getFlag();

    boolean canEnter(Direction direction);

    boolean canExit(Direction direction);

    ITile copy();

    void setFlag(IFlag flag);

    boolean hasWall(Direction dir);
}
