package inf112.skeleton.app.core.interfaces;

import inf112.skeleton.app.core.enums.Direction;

// RT initial setup jan 29/30 - 2019 - cont feb 12
public interface IRobot {

    public Direction getDirection(); // give current direction of robot as ENUM dir();
    public void setDirection(Direction direction); // set direction after action from board (turn from card or tile)

    public int takeEnergy(int energy);    // receive damage (int) and update energy, return new energy (int)
    public int giveEnergy(int energy);    // boost energy (int) from action or card, return new energy (int)
    public int checkEnergy();   // return energy level (int) of current robot

    public void shootLazer();   // fire lazer in current direction (from getDirection();)

}