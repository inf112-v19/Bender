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
        // Can position be found from Robot or board?
        Direction dir = robot.getDirection();
        Position startPosition = board.getRobotPosition(robot);
        int PosX = startPosition.getX();
        int PosY = startPosition.getY();
        // Go from position in direction of dir
        switch (dir){
            // NORTH = y-- until 0
            case NORTH:
            for (int i=PosY; i>=0; i-- ){
                Position checkPosition = new Position(PosX, i);
                if (!(board.getRobot(checkPosition)==null)) {
                    // Robot has been found, damage and return position
                }
                else if (true) {
                    // Find a wall, and return
                    if (Tile.hasWall(board.getTile(checkPosition))) {

                    }
                }

            }
            // SOUTH = y++ until 11
            case SOUTH:
            for (int i=PosY; i<=11; i++){

            }
            // EAST = x++ until 11
            case EAST:
                for (int i=PosX; i<=11; i++)
            {

            }
            // WEST = x-- until 0
            case WEST:
                for (int i=PosX; i>=0; i--)
            {

            }

        }
        // Iterate each tile, and check for wall or robot
        // If found wall, only stop lazer
        // If found robot initiate damage to robot, and then stop lazer


        return lazerShoot;
    }
}
