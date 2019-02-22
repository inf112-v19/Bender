package inf112.skeleton.app.core;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.interfaces.IRobot;

class Robot implements IRobot {

    private int robotEnergy = 100;
    private Direction robotDirection;

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
}
