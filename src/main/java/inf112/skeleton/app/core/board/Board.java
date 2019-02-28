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

    @Override
    public ITile getTile(int x, int y) {
        if (!withinBounds(x, y)) throw new IllegalArgumentException("coordinates out of bounds");
        return this.grid[x][y];
    }

    @Override
    public ITile getTile(Position position) {
        return getTile(position.getX(), position.getY());
    }

    @Override
    public boolean canMove(Direction dir) {
        return false; //TODO: Implement check on neighbors
    }

    @Override
    public void moveRobot(IRobot robot, IProgramCard card) {
        if (card instanceof RotateCard) {
            Direction newDirection = ((RotateCard) card).getDir(robot.getDirection());
            robot.setDirection(newDirection);
        } else if (card instanceof MoveCard) {

        }
    }

    @Override
    public void moveRobot(IRobot robot, Direction dir) {
        Position position = robots.get(robot);
        Position newPosition = dir.getNewPosition(position);
        if (withinBounds(newPosition)) {

        } else {
            // TODO: implement
        }
    }

    private boolean withinBounds(Position position) {
        return withinBounds(position.getX(), position.getY());
    }

    private boolean withinBounds(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x >= this.width || y >= this.height) return false;
        return true;
    }

    public void stepRobots() {
        for (IRobot robot : robots.keySet()) {
            IProgramCard card = robot.drawCard();
            if (card != null) {
                moveRobot(robot, card);
            }
        }
    }

    public void stepTiles() {
        // TODO: Call exec method on all tiles on board
    }

    public void mainStep() {
        this.stepTiles();
        this.stepRobots();
    }
}
