package inf112.skeleton.app.core.player;

import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.Robot;

import java.util.List;
import java.util.ListIterator;

public class Player implements IPlayer {
    private String username;
    private Robot robot;
    private Integer playerNo;
    private List Cards;
    private List cardsToRemove;
    private ProgramCard cardToAdd;
    private Board PlayerBoard;

    // Constructor for player, starting with robot heading NORTH
    public Player(String username) {
        robot = new Robot(Direction.NORTH);
    }

    // Return board of current player
    public Board getBoard() {
        return PlayerBoard;
    }

    // Add card to players list
    public <cardToAdd> void addCard(cardToAdd) {
        Cards.add(cardToAdd);
    }

    // list of max 5 cards to remove
    public <cardsToRemove> void removeCards(cardsToRemove) {
        for (ListIterator<Integer> element = cardsToRemove.listIterator(); element.hasNext(); ) {
            Integer toRemove = element.next();
            Cards.remove(toRemove);
        }
    }

    // Return cards of current player
    public List getCards() {
        return Cards;
    }

    // Give card to robot of current player
    public void giveCardToRobot(IProgramCard card) {
        robot.addCard(card);
    }

    // Return robot of current player
    public Robot getRobot() {
        return robot;
    }

    // Take energy (1) from players robot
    public void takeRobotEnergy() {
        robot.takeEnergy(1);
    }

    // Give energy (1) to players robot
    public void giveRobotEnergy() {
        robot.giveEnergy(1);
    }

    // Return energy of robot of current player
    public int getEnergy() {
        return robot.getEnergy();
    }

}
