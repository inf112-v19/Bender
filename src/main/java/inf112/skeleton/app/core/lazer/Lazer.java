package inf112.skeleton.app.core.lazer;

import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.LaserEvent;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.Robot;
import inf112.skeleton.app.core.sound.Sound;
import inf112.skeleton.app.core.tiles.ITile;
import org.lwjgl.Sys;

import static inf112.skeleton.app.core.enums.Direction.*;

public class Lazer {

    public static Event shootLazer(Robot robot, Board board) throws Exception {
        //Sound effect = new Sound();

        Position checkPosition = new Position(0, 0);
        Direction dir = robot.getDirection();
        Position startPosition = board.getRobotPosition(robot);
        int PosX = startPosition.getX();
        int PosY = startPosition.getY();
        ITile tile = board.getTile(checkPosition);
        boolean foundPosition=false;


        // effect.shootLaser(); // consider placement of function for best syncronization with graphics

        // Go from position in direction of dir and look for Robot or wall
        while ((robot.getDirection() == NORTH)&&(!foundPosition)) {

            for (int i = PosY - 1; i >= 0; i--) {
                checkPosition = new Position(PosX, i);
                tile = board.getTile(checkPosition);

                if (tile.hasRobot()) {
                    // Robot has been found, damage and return position
                    board.getRobot(checkPosition).takeEnergy(1);
                    foundPosition=true;
                    //effect.laserHit(); // consider placement of function for best syncronization with graphics
                    break;
                } else if (tile.hasWall(Direction.SOUTH)) {
                    checkPosition = new Position(PosX, i - 1);
                    foundPosition=true;
                    break;
                } else if (tile.hasWall(NORTH)) {
                    foundPosition=true;
                    break;
                }
            }
        }

        // SOUTH = y++ up to 11
        while ((robot.getDirection() == SOUTH)&&(!foundPosition)){
            for (int i = PosY + 1; i <= 11; i++) {
                checkPosition = new Position(PosX, i);
                tile = board.getTile(checkPosition);
                if (tile.hasRobot()) {
                    // Robot has been found, damage and return position
                    board.getRobot(checkPosition).takeEnergy(1);
                    foundPosition=true;
                    // effect.laserHit(); // consider placement of function for best syncronization with graphics
                    break;
                } else if (tile.hasWall(Direction.SOUTH)) {
                    foundPosition=true;
                    break;
                } else if (tile.hasWall(NORTH)) {
                    checkPosition = new Position(PosX, i - 1);
                    foundPosition=true;
                    break;
                }
            }
        }

        // EAST = x++ up to11
        while ((robot.getDirection() == EAST)&&(!foundPosition)) {
            for (int i = PosX + 1; i <= 11; i++) {
                checkPosition = new Position(PosX, i);
                tile = board.getTile(checkPosition);

                if (tile.hasRobot()) {
                    // Robot has been found, damage and return position
                    board.getRobot(checkPosition).takeEnergy(1);
                    foundPosition=true;

                    //effect.laserHit(); // consider placement of function for best syncronization with graphics
                    break;
                } else if (tile.hasWall(EAST)) {
                    foundPosition=true;
                    break;
                } else if (tile.hasWall(Direction.WEST)) {
                    checkPosition = new Position(i - 1, PosY);
                    foundPosition=true;
                    break;
                }
            }
        }
        // WEST = x-- down to 0
        while ((robot.getDirection() == WEST)&&(!foundPosition)) {
            for (int i = PosX - 1; i >= 0; i--) {
                checkPosition = new Position(PosX, i);
                tile = board.getTile(checkPosition);

                if (tile.hasRobot()) {
                    // Robot has been found, damage and return position
                    board.getRobot(checkPosition).takeEnergy(1);
                    foundPosition=true;
                    //effect.laserHit(); // consider placement of function for best syncronization with graphics
                    break;
                } else if (tile.hasWall(EAST)) {
                    checkPosition = new Position(i - 1, PosY);
                    foundPosition=true;
                    break;
                } else if (tile.hasWall(Direction.WEST)) {
                    foundPosition=true;
                    break;
                }
            }
        }

        // System.out.println(checkPosition);
        // build LaserEvent and return values
        LaserEvent returnEvent = new LaserEvent(startPosition, checkPosition);
        return returnEvent;
    }
}
