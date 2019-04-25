package inf112.skeleton.app.core.Lazer;

import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.LaserEvent;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.Robot;

public class Lazer {

    public Event shootLazer(Robot robot, Board board) {
        LaserEvent lazerShoot;
        // Can position be found from Robot or board?
        Direction dir = robot.getDirection();
        int PosX = board.getRobotPosition(robot).getX();
        int posY = board.getRobotPosition(robot).getY();
        // Go from position in direction of dir
        switch (dir){
            // NORTH = y-- until 0
            case NORTH:
            {for (int i=PosX; i< )}
            // SOUTH = y++ until 11
            case SOUTH:
            {}
            // SOUTH = y++ until 11
            case EAST:
            {}
            // WEST = x-- until 0
            case WEST:
            {}

        }
        // Iterate each tile, and check for wall or robot
        // If found wall, only stop lazer
        // If found robot initiate damage to robot, and then stop lazer


        return lazerShoot;
    }
}
