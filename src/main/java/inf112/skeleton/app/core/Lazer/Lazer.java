package inf112.skeleton.app.core.Lazer;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.Robot;

public class Lazer {

    public void shootLazer(Robot robot) {
        // Can position be found from Robot or board?
        Direction dir = robot.getDirection();
        // Go from position in direction of dir
        // NORTH = y-- until 0
        // SOUTH = y++ until 11
        // EAST = x++ until 11
        // WEST = x-- until 0
        // Iterate each tile, and check for wall or robot
        // If found wall, only stop lazer
        // If found robot initiate damage to robot, and then stop lazer

    }
}
// Shooting lazer needs from position and to position
// And to check if it hits another robot
// And initiate damage on this robot, and stop lazer
// Or only stop lazer if it hits a wall