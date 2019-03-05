package inf112.skeleton.app.core.robot;

import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;

import java.util.ArrayList;

public class Robot implements IRobot {

    private int robotEnergy = 100;
    private Direction robotDirection;
    private ArrayList<ProgramCard> cards = new ArrayList<>();

    public Robot(Direction robotDirection) {
        this.robotDirection = robotDirection;
    }

    @Override
    public Direction getDirection() {
        return robotDirection;
    }

    @Override
    public void setDirection(Direction setDirection) {
        robotDirection = setDirection;
    }

    @Override
    public int takeEnergy(int drawEnergy) {
        robotEnergy -= drawEnergy;
        return robotEnergy;
    }

    @Override
    public int giveEnergy(int giveEnergy) {
        robotEnergy += giveEnergy;
        return robotEnergy;
    }

    @Override
    public int checkEnergy() {
        return robotEnergy;
    }

    @Override
    public void shootLazer() {
        // Unsure how to implement. Must be related to board/tiles somehow
    }

    public void addCard(ProgramCard card) {
        this.cards.add(card);
    }

    public ProgramCard drawCard() {
        if(this.cards.size() == 0) return null;
        return this.cards.remove(this.cards.size()-1);
    }

    @Override
    public int compareTo(IRobot that) {
        int energyDiff = this.checkEnergy() - that.checkEnergy();

        int dirDiff = this.getDirection().ordinal() - that.getDirection().ordinal();

        // TODO: Add checks for program cards?

        return energyDiff + dirDiff;
    }

}
