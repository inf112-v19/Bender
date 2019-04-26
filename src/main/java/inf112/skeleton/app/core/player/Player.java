package inf112.skeleton.app.core.player;

import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.Robot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Player<cardToAdd> implements IPlayer {
    private String username;
    private Robot robot;
    private Integer playerNo;
    private List Cards;
    private List cardsToRemove;
    private IProgramCard cardToAdd;
    private Board PlayerBoard;
    private int nextFlag;

    // Constructor for player, starting with robot heading NORTH
    public Player(String username) {
        robot = new Robot(Direction.NORTH);
    }

    // Return board of current player
    public Board getBoard() {
        return PlayerBoard;
    }

    // Add card to players list
    public void addCard(IProgramCard cardToAdd) {
        Cards.add(cardToAdd);
    }

    // list of max 5 cards to remove
    public void removeCards(List cardsToRemove) {
        for (Iterator<Integer> iter = cardsToRemove.iterator(); iter.hasNext(); ) {
            Integer toRemove = iter.next();
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

    // use to determine how many free slots can be programmed
    public int freeSlots() {
        if (robot.getEnergy() > 4) {
            return 5;
        } else {
            return robot.getEnergy();
        }
    }
        // Return life of player's robot
        public int getLife () {
            return robot.getLife();
        }

        // Take one life from players robot
        public void takeLife () {
            robot.takeLife();
        }

        // Check if robot is alive
        public boolean robotIsAlive () {
            return robot.isAlive();
        }

        // SEND cards of one round to robot
        public boolean programRobot(List cardsToRobot) {
            ArrayList LastRound = new ArrayList();
            ArrayList flippedList = new ArrayList();
            int stuckCards = 5 - freeSlots();
            for (Iterator<Integer> iter = cardsToRobot.iterator(); iter.hasNext(); ) {
                flippedList.add(iter.next());
            }

            // adding "stuck" cards, if user cards is less than 5
            for (int i=0; i<stuckCards; i++) {
                flippedList.add(LastRound.get(i));
            }

            // putting cards in robot memory one by one
            for (int k=5; k>0; k--) {
                robot.addCard((IProgramCard) flippedList.get(k));
            }

            // Copy cards to keep for next round
            LastRound.clear();
            for (int j=5; j<0; j--) {
                LastRound.add(flippedList.get(j));
            }
            return true;
        }

        // gives next flag then user has found a flag
        public int foundFlag() {
            return ++nextFlag;
        }

        // Checks if user has visited the last flag
        public boolean HasWon() {
              if (nextFlag > Board.getNumberOfFlags()) {
                  return true;
             }
            else {
                return false;
            }
        }

    }
