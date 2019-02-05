package inf112.skeleton.app.interfaces;
// RT initial setup jan 29/30 - 2019
public interface IRobot {


    // functionality needed to determine functions according to damage
    public void energy80();     // limits for 80%
    public void energy60();     // limits for 60%
    public void energy40();     // limits for 40%
    public void blockMemory();  // block/lock memory position

    public void move();         // int = -1,1,2,3 (steps)
    public void turn();         // int = 90, 270, 180 degrees
    public void getDirection(); // get current direction of robot as int (0, 90, 280, 270)
    public void giveCards();    // give chosen object(with card ID) of 5 cards to robot, to put in RobotMemory

    public int takeEnergy();    // receive damage (int) and update energy, return new energy (int)
    public int giveEnergy();    // boost energy (int) from action or card, return new energy (int)
    public int checkEnergy();   // return energy level (int) of current robot

    public void shootLazer();   // int = force -> fire lazer in current direction (from getDirection();)

    public void powerDown();    // power down for backup
    public void died();         // mark robot as dead when energy reach 0

}
