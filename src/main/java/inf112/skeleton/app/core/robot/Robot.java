package inf112.skeleton.app.core.robot;

import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;

import java.util.ArrayList;

class Robot implements IRobot {

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
        if(this.cards.size() == 0) return null;
        return this.cards.remove(this.cards.size()-1);
    }

}
