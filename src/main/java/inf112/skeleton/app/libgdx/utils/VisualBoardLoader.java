package inf112.skeleton.app.libgdx.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.MoveEvent;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.Tile;
import inf112.skeleton.app.libgdx.Move;
import inf112.skeleton.app.libgdx.RoboRally;

import java.util.HashSet;
import java.util.List;

public class VisualBoardLoader {

    private int tileWidthHeight;
    private Board board;
    private int boardHeight;
    private int boardWidth;
    private SpriteLoader spriteLoader;

    public VisualBoardLoader(Board board) {
        this.board = board;
        boardHeight = board.getHeight();
        boardWidth = board.getWidth();
        tileWidthHeight = 64;
        this.spriteLoader = new SpriteLoader(tileWidthHeight);
    }

    public int getTileWidthHeight() {
        return tileWidthHeight;
    }

    public void renderBoardCustomSize(SpriteBatch sb, int xStart, int yStart, int width, int height) {
        spriteLoader.setTileSize(width);
        for (int y = 0; y < boardHeight; y++)
            for (int x = 0; x < boardWidth; x++) {
                spriteLoader.drawTile(sb, getTile(x, y), (x * width) + xStart, (y * height) + yStart);
            }
    }

    //The SpriteBatch should be started prior calling this method
    public void renderBoard(SpriteBatch sb, int xStart, int yStart) {
        renderBoardCustomSize(sb, xStart, yStart, 64, 64);
    }

    private Tile getTile(int x, int y) {
        return (Tile) board.getTile(x, y);
    }

    public void disposeTextures() {
        spriteLoader.dispose();
    }

    public void renderRobot(SpriteBatch sb, IRobot robot, IBoard board, int xStart, int yStart, Position pos, boolean roundState) {
        int height = (RoboRally.HEIGHT - 200) / 10;
        if (roundState) {
            spriteLoader.setTileSize(height);
            spriteLoader.drawRobot(sb, robot, board,(pos.getX() * height) + xStart, (pos.getY() * height) + yStart);
        } else {
            spriteLoader.setTileSize(64);
            spriteLoader.drawRobot(sb, robot, board,(pos.getX() * 64) + xStart, (pos.getY() * 64) + yStart);
        }
    }

    public void renderRobots(SpriteBatch sb, Board board, List<Event> events, float progress, int xStart, int yStart) {
        HashSet<IRobot> renderedRobots = new HashSet<>();

        if (events != null) {
            for (Event event : events) {
                if (event instanceof MoveEvent) {
                    MoveEvent moveEvent = (MoveEvent) event;
                    renderedRobots.add(moveEvent.getRobot());
                    renderRobotSlowly(sb, moveEvent.getRobot(), board, xStart, yStart, moveEvent.getStartPosition(), moveEvent.getEndPosition(), progress);
                }
            }
        }

        for (IRobot robot : board.getRobots()) {
            if (renderedRobots.contains(robot)) continue;
            Position position = board.getRobotPosition(robot);
            renderRobotSlowly(sb, robot, board, xStart, yStart, position, position, 0);
        }
    }

    public void renderRobotSlowly(SpriteBatch sb, IRobot robot, IBoard board, int xStart, int yStart, Position start, Position end, float progress) {
        int height = 64;
        int xDistance = (end.getX() - start.getX()) * height;
        int yDistance = (end.getY() - start.getY()) * height;

        float xProgression = progress * xDistance;
        float yProgression = progress * yDistance;

        float currentX = xStart + (start.getX() * height) + xProgression;
        float currentY = yStart + (start.getY() * height) + yProgression;

        spriteLoader.setTileSize(64);
        spriteLoader.drawRobot(sb, robot, board, currentX, currentY);
    }

    public Position getRobotPos() {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.hasRobot(new Position(x, y)))
                    return new Position(x, y);
            }
        }
        return null;
    }
}

