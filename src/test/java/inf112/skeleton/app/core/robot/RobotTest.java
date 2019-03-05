package inf112.skeleton.app.core.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import inf112.skeleton.app.core.enums.Direction;
import org.junit.Test;


public class RobotTest {

    public Robot robotBender = new Robot(Direction.NORTH);

    @Test
    public void takeRobotEnergyTest() {
        int energy = 20;
        robotBender.takeEnergy(energy);
        assertEquals(robotBender.getEnergy(),100-energy);
    }

    @Test
    public void giveRobotEnergyTest() {
        robotBender.giveEnergy(20);
        assertEquals(robotBender.getEnergy(),120);
    }

    @Test
    public void setDirectionShouldGiveDirectionOfRobotTest() {
        robotBender.setDirection(Direction.WEST);
        assertEquals(robotBender.getDirection(),Direction.WEST);
    }

    @Test
    public void takeTwoTimesEnergyGivesDifferenceTest() {
        robotBender.takeEnergy(20);
        robotBender.takeEnergy(20);
        assertEquals(robotBender.getEnergy(),60);
    }
}
