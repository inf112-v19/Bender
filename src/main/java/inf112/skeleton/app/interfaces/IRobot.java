package inf112.skeleton.app.interfaces;

import inf112.skeleton.app.enums.Direction;

// RT initial setup jan 29/30 - 2019 - cont feb 12
public interface IRobot {

    public Direction getDirection(); // give current direction of robot as ENUM dir();
    public void setDirection(Direction); // set direction after action from board (turn from card or tile)

    void setDirection(Direction setDirection);

    public int takeEnergy(int energy);    // receive damage (int) and update energy, return new energy (int)
    public int giveEnergy(int energy);    // boost energy (int) from action or card, return new energy (int)
    public int checkEnergy();   // return energy level (int) of current robot

    public void shootLazer();   // fire lazer in current direction (from getDirection();)

}