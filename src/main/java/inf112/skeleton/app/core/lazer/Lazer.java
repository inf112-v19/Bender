package inf112.skeleton.app.core.lazer;

import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.LaserEvent;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.Robot;

public class Lazer {

    public Event shootLazer(Robot robot, Board board) {
        LaserEvent lazerShoot;
        // Can position be found from Robot or board?
        Direction dir = robot.getDirection();
        int PosX = board.getRobotPosition(robot).getX();
        int PosY = board.getRobotPosition(robot).getY();
        // Go from position in direction of dir
        switch (dir){
            // NORTH = y-- until 0
            case NORTH:
            for (int i=PosY; i>=0; i-- ){

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
