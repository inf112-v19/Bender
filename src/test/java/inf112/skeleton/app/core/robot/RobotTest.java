package inf112.skeleton.app.core.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.cards.RotateCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.Robot;
import org.junit.Test;


public class RobotTest {

    public Robot robotBender = new Robot(Direction.NORTH);

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

    @Test
    public void giveCardToRobotGivesOneCardBack() {
        // TODO
    }

    public void giveTwoCardsAndTakeGivesNull() {
        // TODO
    }
}
