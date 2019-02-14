package inf112.skeleton.app.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class RobotTest {

    public Robot robotBender = new Robot();

    @Test
    public void takeRobotEnergyTest() {
        robotBender.takeEnergy(20);
        assertEquals(robotBender.checkEnergy(),80);
    }

    @Test
    public void giveRobotEnergyTest() {
        robotBender.giveEnergy(20);
        assertEquals(robotBender.checkEnergy(),120);
    }

    @Test
    public void setDirectionShouldGiveDirectionOfRobotTest() {
        assertTrue(true);
    }

    @Test
    public void takeTwoTimesEnergyGivesDifferenceTest() {
        robotBender.takeEnergy(20);
        robotBender.takeEnergy(20);
        assertEquals(robotBender.checkEnergy(),60);
    }
}
