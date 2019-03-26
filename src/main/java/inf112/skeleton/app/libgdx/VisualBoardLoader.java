package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.tiles.Tile;
import inf112.skeleton.app.core.tiles.TileAssemblyLine;
import inf112.skeleton.app.core.tiles.TileGear;
import inf112.skeleton.app.libgdx.utils.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//TODO add comments
public class VisualBoardLoader {
    private Map<Integer, String> tilePathNameToNumber;
    private Texture[] tileTextures;
    private int tileWidthHeight;
    private Board board;
    private int boardHeight;
    private int boardWidth;

    private SpriteLoader spriteLoader;

    public VisualBoardLoader(Board board) throws IOException {
        this.board = board;
        boardHeight = board.getHeight();
        boardWidth = board.getWidth();
        tileTextures = new Texture[15];
        tilePathNameToNumber = new HashMap<>();
        tileWidthHeight = 64;
        this.spriteLoader = new SpriteLoader(tileWidthHeight);
        makeMap();
        initializeTextures();
    }

    public int getTileWidthHeight() {
        return tileWidthHeight;
    }

    private void initializeTextures() {
        for (int i = 0; i < tileTextures.length; i++)
            tileTextures[i] = resizeTexture(64, 64, tilePathNameToNumber.get(i));
    }

    public void initializeCustomSizeTextures(int width, int height) {
        tileWidthHeight = height;
        for (int i = 0; i < tileTextures.length; i++)
            tileTextures[i] = resizeTexture(width, height, tilePathNameToNumber.get(i));
    }

    public void renderBoardCustomSize(SpriteBatch sb, int xStart, int yStart, int width, int height) {
        initializeCustomSizeTextures(width, height);
        for (int y = 0; y < boardHeight; y++)
            for (int x = 0; x < boardWidth; x++)
                drawCorrsepnndingTiles(getTile(x, y), sb, (x * width) + xStart, (y * height) + yStart);
    }

    //The SpriteBatch should be started prior calling this method
    public void renderBoard(SpriteBatch sb, int xStart, int yStart) {
        for (int y = 0; y < boardHeight; y++)
            for (int x = 0; x < boardWidth; x++) {
                // drawCorrsepnndingTiles(getTile(x, y), sb, (x * 64) + xStart, (y * 64) + yStart);
                sb.draw(spriteLoader.getTileSprite(getTile(x, y)), (x * 64) + xStart, (y * 64) + yStart);
            }
    }

    //Cases will need to be changed accordingly to the new implementation of  getTexture method
    private void drawCorrsepnndingTiles(Tile tile, SpriteBatch sb, int x, int y) {
        if (tile instanceof TileAssemblyLine) {
            TileAssemblyLine tileAssemblyLine = (TileAssemblyLine) tile;
            switch (tileAssemblyLine.getDirection()) {
                case NORTH:
                    sb.draw(getTexture(tile, false, true, Direction.NORTH), x, y);
                case SOUTH:
                    sb.draw(getTexture(tile, false, true, Direction.SOUTH), x, y);
                case EAST:
                    sb.draw(getTexture(tile, false, true, Direction.EAST), x, y);
                case WEST:
                    sb.draw(getTexture(tile, false, true, Direction.WEST), x, y);
            }
        } else if (tile instanceof TileGear) {
            TileGear tileGear = (TileGear) tile;
            switch (tileGear.getAngle()) {
                case LEFT:
                    sb.draw(getTexture(tile, true, false, Direction.NORTH), x, y);
                case RIGHT:
                    sb.draw(getTexture(tile, true, false, Direction.SOUTH), x, y);
                case UTURN:
                    sb.draw(getTexture(tile, true, false, Direction.EAST), x, y);
            }
        } else {
            sb.draw(getEmptyTexture(), x, y);
        }
    }

    private Tile getTile(int x, int y) {
        return (Tile) board.getTile(x, y);
    }

    //TODO
    private Texture getTexture(Tile tile, boolean isTIleGear, boolean isTileAssemblyLine, Direction dir) {
        return tileTextures[2];
    }

    public void disposeTextures() {
        spriteLoader.dispose();
    }

    private Texture getEmptyTexture() {
        return tileTextures[0];
    }

    private Texture resizeTexture(int newWidth, int newHeight, String path) {
        Pixmap pixmapOld = new Pixmap(Gdx.files.internal(path));
        Pixmap pixmapNew = new Pixmap(newWidth, newHeight, pixmapOld.getFormat());
        pixmapNew.drawPixmap(pixmapOld,
                0, 0, pixmapOld.getWidth(), pixmapOld.getHeight(),
                0, 0, pixmapNew.getWidth(), pixmapNew.getHeight()
        );

        return new Texture(pixmapNew);
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    public void renderRobot(SpriteBatch sb, int xStart, int yStart, Position pos, boolean roundState) {
        int height = (RobotDemo.HEIGHT - 200) / 10;
        if (roundState)
            sb.draw(tileTextures[2], (pos.getX() * height) + xStart, (pos.getY() * height) + yStart);
        else
            sb.draw(tileTextures[2], (pos.getX() * 64) + xStart, (pos.getY() * 64) + yStart);
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

    private void makeMap() {
        tilePathNameToNumber.put(0, "tiles/empty_tile.png");
        tilePathNameToNumber.put(1, "tiles/rotate_left_tile.png");
        tilePathNameToNumber.put(2, "tiles/rotate_right_tile.png");
        tilePathNameToNumber.put(3, "tiles/assembly_orange_straight_down_tile.png");
        tilePathNameToNumber.put(4, "tiles/assembly_orange_straight_up_tile.png");
        tilePathNameToNumber.put(5, "tiles/assembly_orange_straight_right_tile.png");
        tilePathNameToNumber.put(6, "tiles/assembly_orange_straight_left_tile.png");

        tilePathNameToNumber.put(7, "tiles/assembly_orange_turn_left_up_tile.png");
        tilePathNameToNumber.put(8, "tiles/assembly_orange_turn_left_down_tile.png");
        tilePathNameToNumber.put(9, "tiles/assembly_orange_turn_right_up_tile.png");
        tilePathNameToNumber.put(10, "tiles/assembly_orange_turn_right_down_tile.png");
        tilePathNameToNumber.put(11, "tiles/assembly_orange_turn_down_right_tile.png");
        tilePathNameToNumber.put(12, "tiles/assembly_orange_turn_down_left_tile.png");
        tilePathNameToNumber.put(13, "tiles/assembly_orange_turn_up_left_tile.png");
        tilePathNameToNumber.put(14, "tiles/assembly_orange_turn_up_right_tile.png");
    }
}

