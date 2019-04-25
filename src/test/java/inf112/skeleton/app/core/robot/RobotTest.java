package inf112.skeleton.app.core.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.cards.RotateCard;
import inf112.skeleton.app.core.enums.Direction;
import org.junit.Assert;
import org.junit.Test;

public class RobotTest {

    public Robot robotBender = new Robot(Direction.NORTH);

    @Test
    public void takeRobotEnergyTest() {
        int energy = 1;
        robotBender.takeEnergy(energy);
        assertEquals(robotBender.getEnergy(),7-energy);
    }

    @Test
    public void giveRobotEnergyTest() {
        robotBender.giveEnergy(2);
        assertEquals(robotBender.getEnergy(),10);
    }

    @Test
    public void setDirectionShouldGiveDirectionOfRobotTest() {
        robotBender.setDirection(Direction.WEST);
        assertEquals(robotBender.getDirection(),Direction.WEST);
    }

    @Test
    public void takeTwoTimesEnergyGivesDifferenceTest() {
        robotBender.takeEnergy(2);
        robotBender.takeEnergy(2);
        assertEquals(robotBender.getEnergy(),4);
    }

    @Test
    public void giveCardToRobotGivesOneCardBack() {
        robotBender.addCard(new MoveCard(8, false, 2));
        assertTrue(robotBender.getNumberOfCards()==1);
        robotBender.drawCard();
        assertTrue(robotBender.getNumberOfCards()==0);
    }

    @Test
    public void giveTwoCardsAndTakeGivesZero() {
        robotBender.addCard(new MoveCard(8, false, 2));
        robotBender.addCard(new MoveCard(20, true, 1));
        assertTrue(robotBender.getNumberOfCards()==2);
        robotBender.drawCard();
        robotBender.drawCard();
        assertTrue(robotBender.getNumberOfCards()==0);
    }

    // Test .drawCard to give same card as .addCard

    //
}
