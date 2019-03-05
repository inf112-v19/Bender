package inf112.skeleton.app.core.robot;

import inf112.skeleton.app.core.enums.Direction;

// RT initial setup jan 29/30 - 2019 - cont feb 12
/**
 * IRobot extends Comparable just so IRobot can be compared in the tests
 */
public interface IRobot extends Comparable<IRobot> {

    Direction getDirection(); // give current direction of robot as ENUM dir();
    void setDirection(Direction direction); // set direction after action from board (turn from card or tile)

    int takeEnergy(int energy);    // receive damage (int) and update energy, return new energy (int)
    int giveEnergy(int energy);    // boost energy (int) from action or card, return new energy (int)
    int checkEnergy();   // return energy level (int) of current robot

    void shootLazer();   // fire lazer in current direction (from getDirection();)

}