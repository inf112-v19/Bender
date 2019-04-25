package inf112.skeleton.app.core.lazer;

import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.LaserEvent;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.Robot;
import inf112.skeleton.app.core.tiles.Tile;

public class Lazer {

    public Event shootLazer(Robot robot, Board board) {
        LaserEvent lazerShoot;
        Position checkPosition = new Position(0,0);
        // Can position be found from Robot or board?
        Direction dir = robot.getDirection();
        Position startPosition = board.getRobotPosition(robot);
        int PosX = startPosition.getX();
        int PosY = startPosition.getY();
        // Go from position in direction of dir
        switch (dir){
            // NORTH = y-- down to 0
            case NORTH:
            for (int i=PosY; i>=0; i-- ){
                checkPosition = new Position(PosX, i);
                if (!(board.getRobot(checkPosition)==null)) {
                    // Robot has been found, damage and return position
                    board.getRobot(checkPosition).takeEnergy(1);
                }
                else if (true) {
                    // Find a wall, and return
                    Tile tile = board.getTile(checkPosition)
                    // if (tile.hasWall) {}
                }
            }
            // SOUTH = y++ up to 11
            case SOUTH:
            for (int i=PosY; i<=11; i++){
                checkPosition = new Position(PosX, i);
                if (!(board.getRobot(checkPosition)==null)) {
                    // Robot has been found, damage and return position
                    board.getRobot(checkPosition).takeEnergy(1);
                }
                else if (true) {
                    // Find a wall, and return
                    Tile tile = board.getTile(checkPosition)
                    // if (tile.hasWall) {}
                }
            }
            // EAST = x++ up to11
            case EAST:
                for (int i=PosX; i<=11; i++) {
                    checkPosition = new Position(i, PosY);
                    if (!(board.getRobot(checkPosition)==null)) {
                        // Robot has been found, damage and return position
                        board.getRobot(checkPosition).takeEnergy(1);
                    }
                    else if (true) {
                        // Find a wall, and return
                        Tile tile = board.getTile(checkPosition)
                        // if (tile.hasWall) {}
                    }
            }
            // WEST = x-- down to 0
            case WEST:
                for (int i=PosX; i>=0; i--) {
                    checkPosition = new Position(i, PosY);
                    if (!(board.getRobot(checkPosition)==null)) {
                        // Robot has been found, damage and return position
                        board.getRobot(checkPosition).takeEnergy(1);
                    }
                    else if (true) {
                        // Find a wall, and return
                        Tile tile = board.getTile(checkPosition)
                        // if (tile.hasWall) {}
                    }
            }

        }

        // build Laserevent and return values
        LaserEvent returnEvent = new LaserEvent(startPosition,checkPosition);
        return returnEvent;
    }
}
