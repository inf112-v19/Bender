package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.Position;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.interfaces.IRobot;
import inf112.skeleton.app.core.tiles.Tile;
import java.util.HashMap;

public class Board implements IBoard {

    private int width;
    private int height;

    private Tile[][] grid;
    private HashMap<IRobot, Position> robots;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        this.robots = new HashMap<>();
    }

    @Override
    public Tile getTile(int x, int y) {
        if (!withinBounds(x, y)) throw new IllegalArgumentException("coordinates out of bounds");
        return this.grid[x][y];
    }

    @Override
    public Tile getTile(Position position) {
        return getTile(position.getX(), position.getY());
    }

    @Override
    public void moveRobot(IRobot robot, IProgramCard card) {
        // TODO: implement
    }

    @Override
    public void moveRobot(IRobot robot, Direction dir) {
        Position position = robots.get(robot);
        Position newPosition = dir.getNewPosition(position);
        if (withinBounds(newPosition)) {
            // TODO: implement
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
}
