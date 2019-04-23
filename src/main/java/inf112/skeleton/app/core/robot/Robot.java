package inf112.skeleton.app.core.robot;


import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.cards.ProgramDeck;
import inf112.skeleton.app.core.enums.Direction;

import java.util.ArrayList;

public class Robot implements IRobot, Comparable<IRobot> {

    private int robotEnergy = 100;
    private Direction robotDirection;
    private ArrayList<IProgramCard> cards = new ArrayList<>();

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

    public void setEnergy(int energy) {
        this.robotEnergy = energy;
    }

    @Override
    public int giveEnergy(int giveEnergy) {
        robotEnergy += giveEnergy;
        return robotEnergy;
    }

    @Override
    public int getEnergy() {
        return robotEnergy;
    }

    @Override
    public void shootLazer() {
        // TODO: implement
    }

    public void addCard(IProgramCard card) {
        this.cards.add(card);
    }

    public IProgramCard drawCard() {
        if (this.cards.size() == 0) return null;
        return this.cards.remove(this.cards.size() - 1);
    }

    public IProgramCard peekCard() {
        if (this.cards.size() == 0) return null;
        return this.cards.get(this.cards.size() - 1);
    }

    // OUTPUTS number of cards in Robot-memory
    public int getNumberOfCards() {
        return this.cards.size();
    }

    // OUTPUTS the whole array of cards
    public ArrayList getCardArray() {
        return this.cards;
    }

    @Override
    public int compareTo(IRobot that) {
        int energyDiff = this.getEnergy() - that.getEnergy();

        int dirDiff = this.getDirection().ordinal() - that.getDirection().ordinal();

        // TODO: Add checks for program cards?
        // can use routine above "getNumberOfCards" or "getCardArray" to compare cards of two robots


        return energyDiff + dirDiff;
    }

    public Robot copy() {
        Robot newRobot = new Robot(robotDirection);
        newRobot.setEnergy(robotEnergy);
        ArrayList<IProgramCard> cards = new ArrayList<>();
        for (int i = 0; i < this.cards.size(); i++) {
            cards.add(this.cards.get(i).copy());
        }
        newRobot.setProgramCards(cards);
        return newRobot;
    }

    public void setProgramCards(ArrayList<IProgramCard> newCards) {
        this.cards = newCards;
    }
}
