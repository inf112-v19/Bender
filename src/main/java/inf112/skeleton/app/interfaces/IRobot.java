package inf112.skeleton.app.interfaces;
// RT initial setup jan 29/30 - 2019
public interface IRobot {

    public void move();         // int = -1,0,1,2,3 (steps)  char = N,S,E,W (direction) or int = 0, 90, 180, 270
    public void turn();         // int = 90, 270, 180 degrees
    public void getDirection(); // get current direction of robot as char (N,S,E,W) or int (0, 90, 280, 270)
    public void giveCards();    // give chosen object(with card ID) of 5 cards to robot, to put in RobotMemory
  
    public int takeEnergy();    // receive damage and update energy, return new energy
    public int giveEnergy();    // boost energy from action or card, return new energy
    public int checkEnergy();   // return energy level of current robot
  
    public void shootLazer();   // char = direction, int = force -> fire lazer in current direction (fom getDirection();)
    
    public void powerDown();    // power down for backup
    public void died();         // mark robot as dead when energy reach 0
    
}
