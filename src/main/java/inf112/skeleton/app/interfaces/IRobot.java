package inf112.skeleton.app.interfaces;
// RT initial setup jan 29/30 - 2019 - cont feb 12
public interface IRobot {

    public void getDirection(); // give current direction of robot as ENUM dir();
    public void setDirection(); // set direction after action from board (turn from card or tile)
    public void giveCards();    // give chosen object(with card ID) of 5 cards to robot, to put in RobotMemory

    public int takeEnergy();    // receive damage (int) and update energy, return new energy (int)
    public int giveEnergy();    // boost energy (int) from action or card, return new energy (int)
    public int checkEnergy();   // return energy level (int) of current robot

    public void shootLazer();   // int = force -> fire lazer in current direction (from getDirection();)

    public void powerDown();    // power down for backup

}