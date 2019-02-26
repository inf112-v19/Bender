package inf112.skeleton.app.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import inf112.skeleton.app.enums.Direction;
import org.junit.Before;
import org.junit.Test;


public class RobotTest {

    public Robot robotBender = new Robot();

    @Test
    public void takeRobotEnergyTest() {
        int energy = 20;
        robotBender.takeEnergy(energy);
        assertEquals(robotBender.checkEnergy(),100-energy);
    }

    @Test
    public void giveRobotEnergyTest() {
        robotBender.giveEnergy(20);
        assertEquals(robotBender.checkEnergy(),120);
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
        assertEquals(robotBender.checkEnergy(),60);
    }
}
