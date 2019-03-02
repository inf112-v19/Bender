package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.BoardLoader;
import inf112.skeleton.app.core.tiles.Tile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VisualBoardLoader {
    private BoardLoader boardloader;
    private Map<Integer, String> tilePathNameToNumber;
    private Texture[] tileTextures;
    int[][] tiles;

    public VisualBoardLoader(String filepath) throws IOException {
        tileTextures = new Texture[15];
        tilePathNameToNumber = new HashMap<>();
        boardloader = new BoardLoader();

        makeMap();
        initializeTextures();

        tiles = boardloader.loadFile(filepath);
    }

    private void initializeTextures() {
        for (int i = 0; i < tileTextures.length; i++) {
            tileTextures[i] = resizeTexture(64, 64, tilePathNameToNumber.get(i));
//            tileTextures[i] = new Texture(Gdx.files.internal(tilePathNameToNumber.get(i)));
        }
    }

    //The SpriteBatch should be started prior calling this method
    public void renderBoard(SpriteBatch sb) {
        for (int y = 0; y < tiles.length; y++)
            for (int x = 0; x < tiles[y].length; x++)
                sb.draw(tileTextures[tiles[x][y]], x * 64, y * 64);
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
        tilePathNameToNumber.put(0, "res/tiles/empty_tile.png");
        tilePathNameToNumber.put(1, "res/tiles/rotate_left_tile.png");
        tilePathNameToNumber.put(2, "res/tiles/rotate_right_tile.png");
        tilePathNameToNumber.put(3, "res/tiles/assembly_orange_straight_down_tile.png");
        tilePathNameToNumber.put(4, "res/tiles/assembly_orange_straight_up_tile.png");
        tilePathNameToNumber.put(5, "res/tiles/assembly_orange_straight_right_tile.png");
        tilePathNameToNumber.put(6, "res/tiles/assembly_orange_straight_left_tile.png");

        tilePathNameToNumber.put(7, "res/tiles/assembly_orange_turn_left_up_tile.png");
        tilePathNameToNumber.put(8, "res/tiles/assembly_orange_turn_left_down_tile.png");
        tilePathNameToNumber.put(9, "res/tiles/assembly_orange_turn_right_up_tile.png");
        tilePathNameToNumber.put(10, "res/tiles/assembly_orange_turn_right_down_tile.png");
        tilePathNameToNumber.put(11, "res/tiles/assembly_orange_turn_down_right_tile.png");
        tilePathNameToNumber.put(12, "res/tiles/assembly_orange_turn_down_left_tile.png");
        tilePathNameToNumber.put(13, "res/tiles/assembly_orange_turn_up_left_tile.png");
        tilePathNameToNumber.put(14, "res/tiles/assembly_orange_turn_up_right_tile.png");
    }
}

