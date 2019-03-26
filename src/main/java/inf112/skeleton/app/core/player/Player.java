package inf112.skeleton.app.core.player;

import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.Robot;

public class Player implements IPlayer {
    String username;
    Robot robot;
    Integer playerNo;

    public Player(String username) {
        robot = new Robot(Direction.NORTH);
    }
    public Robot getRobot() {
        return robot;
    }
    public void getBoard() {
        // TODO
    }
    public void giveCardToRobot(IProgramCard card) {
        robot.addCard(card);
    }
}
