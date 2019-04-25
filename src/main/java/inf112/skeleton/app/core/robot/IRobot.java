package inf112.skeleton.app.core.robot;

import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.flag.IFlag;

import java.util.ArrayList;
import java.util.UUID;

public interface IRobot extends Comparable<IRobot> {

    /**
     * @return current direction of robot
     */
    Direction getDirection();

    /**
     * Change the direction of the robot
     * @param direction
     */
    void setDirection(Direction direction);

    /**
     * Damage robot and
     * @param energy
     * @return new energy level
     */
    int takeEnergy(int energy);

    /**
     * Add energy to robot
     * @param energy
     * @return new energy level
     */
    int giveEnergy(int energy);

    /**
     * @return current energy level
     */
    int getEnergy();

    /**
     * Fire lazer in current direction
      */
    void shootLazer();

    /**
     * Add a programcard to the robots 'register'
     * @param card
     */
    void addCard(IProgramCard card);

    /**
     * 'Draw' a program card from the robots register
     * @return
     */
    IProgramCard drawCard();

    IProgramCard peekCard();

    IRobot copy();

    void setEnergy(int energy);

    void setProgramCards(ArrayList<IProgramCard> cards);

    UUID getId();

    void setId(UUID newId);
}