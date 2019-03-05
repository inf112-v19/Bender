package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.BoardLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//TODO add comments
public class VisualBoardLoader {
    private Map<Integer, String> tilePathNameToNumber;
    private Texture[] tileTextures;
    private int[][] tiles;
    private int tileWidthHeight;

    public VisualBoardLoader(String filepath) throws IOException {
        tileTextures = new Texture[15];
        tilePathNameToNumber = new HashMap<>();
        BoardLoader boardloader = new BoardLoader();
        tileWidthHeight = 64;

        makeMap();
        initializeTextures();

        tiles = boardloader.loadFile(filepath);
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
        for (int y = 0; y < tiles.length; y++)
            for (int x = 0; x < tiles[y].length; x++)
                sb.draw(tileTextures[tiles[x][y]], (x * width) + xStart, (y * height) + yStart);
    }

    //The SpriteBatch should be started prior calling this method
    public void renderBoard(SpriteBatch sb, int xStart, int yStart) {
        for (int y = 0; y < tiles.length; y++)
            for (int x = 0; x < tiles[y].length; x++)
                sb.draw(tileTextures[tiles[x][y]], (x * 64) + xStart, (y * 64) + yStart);
    }

    private Texture resizeTexture(int newWidth, int newHeight, String path) {
        Pixmap pixmapOld = new Pixmap(Gdx.files.internal(path));
        Pixmap pixmapNew = new Pixmap(newWidth, newHeight, pixmapOld.getFormat());
        pixmapNew.drawPixmap(pixmapOld,
                0, 0, pixmapOld.getWidth(), pixmapOld.getHeight(),
                0, 0, pixmapNew.getWidth(), pixmapNew.getHeight()
        );
//        pixmapOld.dispose();
//        pixmapNew.dispose();
        return new Texture(pixmapNew);
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

