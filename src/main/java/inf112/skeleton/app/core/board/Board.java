package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.RotateCard;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.ITile;

import java.util.HashMap;

public class Board implements IBoard {

    private int width;
    private int height;

    private ITile[][] grid;
    private HashMap<IRobot, Position> robots;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new ITile[width][height];
        this.robots = new HashMap<>();
    }

    public void mainStep() {
        this.stepTiles();
        this.stepRobots();
    }

    public void stepRobots() {
        // TODO: execute cards in prioritized order
        for (IRobot robot : robots.keySet()) {
            IProgramCard card = robot.drawCard();
            if (card != null) {
                moveRobot(robot, card);
            }
        }
    }

    public void stepTiles() {
        // TODO: Call exec method on all tiles on board
        /*
            NOTE:
            it is important that all tiles are executed 'at the same time'
            such that if one tile moves a robot to another, that tile can't move it again
         */
    }

    @Override
    public void moveRobot(IRobot robot, IProgramCard card) {
        if (card instanceof RotateCard) {
            RotateCard rotateCard = (RotateCard) card;
            Direction newDirection = rotateCard.getDir(robot.getDirection());
            robot.setDirection(newDirection);

        } else if (card instanceof MoveCard) {
            MoveCard moveCard = (MoveCard) card;
            Direction dir = robot.getDirection();
            int amount = moveCard.getAmmount();
            if (moveCard.movesBackwards()) dir = dir.getOpposite();
            moveRobot(robot, dir, amount);
        }
    }

    @Override
    public boolean moveRobot(IRobot robot, Direction dir, int amount) {
        if (amount == 0) return true;

        Position currentPosition = robots.get(robot);
        ITile currentTile = getTile(currentPosition);
        Position newPosition = dir.getNewPosition(currentPosition);

        if (withinBounds(newPosition)) {
            ITile nextTile = getTile(newPosition);
            if (nextTile.canEnter(dir)) { // if there are no walls
                if (nextTile.hasRobot()) {
                    if (moveRobot(nextTile.getRobot(), dir, 1)) {
                        moveRobotToNewTile(currentTile, nextTile);
                        return moveRobot(robot, dir, amount - 1);
                    }
                } else {
                    // if the new tile is empty and has no walls
                    moveRobotToNewTile(currentTile, nextTile);
                    return moveRobot(robot, dir, amount - 1);
                }
            }
        } else {
            // TODO: implement what happens if a robot leaves the board
        }

        return false;
    }

    /**
     * HELPER METHODS
     */

    private void moveRobotToNewTile(ITile from, ITile to) {
        if (!from.hasRobot()) throw new IllegalArgumentException("can't move a robot from a tile that has no robot");
        IRobot robot = from.getRobot();
        to.setRobot(robot);
        from.setRobot(null);
    }

    private boolean withinBounds(Position position) {
        return withinBounds(position.getX(), position.getY());
    }

    private boolean withinBounds(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x >= this.width || y >= this.height) return false;
        return true;
    }

    @Override
    public ITile getTile(Position position) {
        return getTile(position.getX(), position.getY());
    }

    @Override
    public ITile getTile(int x, int y) {
        if (!withinBounds(x, y)) throw new IllegalArgumentException("coordinates out of bounds");
        return this.grid[x][y];
    }
}
