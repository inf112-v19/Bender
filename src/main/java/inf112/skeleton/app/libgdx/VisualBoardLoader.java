package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.Tile;
import inf112.skeleton.app.libgdx.utils.SpriteLoader;

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

    public void renderRobot(SpriteBatch sb, IRobot robot, int xStart, int yStart, Position pos, boolean roundState) {
        int height = (RobotDemo.HEIGHT - 200) / 10;
        if (roundState) {
            spriteLoader.setTileSize(height);
            spriteLoader.drawRobot(sb, robot,(pos.getX() * height) + xStart, (pos.getY() * height) + yStart);
        } else {
            spriteLoader.setTileSize(64);
            spriteLoader.drawRobot(sb, robot,(pos.getX() * 64) + xStart, (pos.getY() * 64) + yStart);
        }
    }

    public void renderRobotSlowly(SpriteBatch sb, IRobot robot, int xStart, int yStart, Position start, Position end, float progress) {
        int height = 64;
        int xDistance = (end.getX() - start.getX()) * height;
        int yDistance = (end.getY() - start.getY()) * height;

        float xProgression = progress * xDistance;
        float yProgression = progress * yDistance;

        float currentX = xStart + (start.getX() * height) + xProgression;
        float currentY = yStart + (start.getY() * height) + yProgression;

        spriteLoader.setTileSize(64);
        spriteLoader.drawRobot(sb, robot, currentX, currentY);
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

