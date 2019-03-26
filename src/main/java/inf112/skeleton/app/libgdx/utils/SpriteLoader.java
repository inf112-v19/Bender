package inf112.skeleton.app.libgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.ITile;
import inf112.skeleton.app.core.tiles.TileAssemblyLine;
import inf112.skeleton.app.core.tiles.TileBlackhole;
import inf112.skeleton.app.core.tiles.TileGear;

import java.util.HashMap;

public class SpriteLoader {

    private HashMap<String, Sprite> sprites;

    private int tileSize = 96;

    public SpriteLoader() {
        initializeSprites();
    }

    public SpriteLoader(int tileSize) {
        this.tileSize = 96;
        initializeSprites();
    }

    private void initializeSprites() {
        sprites = new HashMap<>();
        Texture tiles = new Texture(Gdx.files.internal("tiles/tiles.png"));
        int tileSize = 96;

        Sprite empty                    = new Sprite(new TextureRegion(tiles, 4 * tileSize, 0  * tileSize, tileSize, tileSize));
        Sprite blackHole                = new Sprite(new TextureRegion(tiles, 5 * tileSize, 0  * tileSize, tileSize, tileSize));
        Sprite fastAssemblyForward      = new Sprite(new TextureRegion(tiles, 4 * tileSize, 1  * tileSize, tileSize, tileSize)); // rotate
        Sprite fastAssemblyTurnLeft     = new Sprite(new TextureRegion(tiles, 0 * tileSize, 2  * tileSize, tileSize, tileSize)); // rotate
        Sprite fastAssemblyTurnRight    = new Sprite(new TextureRegion(tiles, 4 * tileSize, 2  * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyTurnLeft         = new Sprite(new TextureRegion(tiles, 0 * tileSize, 4  * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyTurnRight        = new Sprite(new TextureRegion(tiles, 2 * tileSize, 4  * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyLineForward      = new Sprite(new TextureRegion(tiles, 0 * tileSize, 6  * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyFromRight        = new Sprite(new TextureRegion(tiles, 0 * tileSize, 8  * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyFromLeft         = new Sprite(new TextureRegion(tiles, 0 * tileSize, 7  * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyFromBoth         = new Sprite(new TextureRegion(tiles, 0 * tileSize, 5  * tileSize, tileSize, tileSize)); // rotate
        Sprite fastAssemblyFromRight    = new Sprite(new TextureRegion(tiles, 4 * tileSize, 9  * tileSize, tileSize, tileSize)); // rotate
        Sprite fastAssemblyFromLeft     = new Sprite(new TextureRegion(tiles, 0 * tileSize, 9  * tileSize, tileSize, tileSize)); // rotate
        Sprite fastAssemblyFromBoth     = new Sprite(new TextureRegion(tiles, 0 * tileSize, 10 * tileSize, tileSize, tileSize)); // rotate
        Sprite rotateLeft               = new Sprite(new TextureRegion(tiles, 4 * tileSize, 6  * tileSize, tileSize, tileSize));
        Sprite rotateRight              = new Sprite(new TextureRegion(tiles, 5 * tileSize, 0  * tileSize, tileSize, tileSize));
        Sprite wall                     = new Sprite(new TextureRegion(tiles, 4 * tileSize, 3  * tileSize, tileSize, tileSize)); // rotate

        sprites.put("empty", empty);
        sprites.put("blackHole", blackHole);
        sprites.put("fastAssemblyForward", fastAssemblyForward);
        sprites.put("fastAssemblyTurnLeft", fastAssemblyTurnLeft);
        sprites.put("fastAssemblyTurnRight", fastAssemblyTurnRight);
        sprites.put("assemblyTurnLeft", assemblyTurnLeft);
        sprites.put("assemblyTurnRight", assemblyTurnRight);
        sprites.put("assemblyLineForward", assemblyLineForward);
        sprites.put("assemblyFromRight", assemblyFromRight);
        sprites.put("assemblyFromLeft", assemblyFromLeft);
        sprites.put("assemblyFromBoth", assemblyFromBoth);
        sprites.put("fastAssemblyFromRight", fastAssemblyFromRight);
        sprites.put("fastAssemblyFromLeft", fastAssemblyFromLeft);
        sprites.put("fastAssemblyFromBoth", fastAssemblyFromBoth);
        sprites.put("rotateLeft", rotateLeft);
        sprites.put("rotateRight", rotateRight);
        sprites.put("wall", wall);

        Sprite robot = new Sprite(new Texture(Gdx.files.internal("tiles/robot.png")));
        sprites.put("robot", robot);
    }

    private Sprite getSprite(String name) {
        Sprite sprite = sprites.get(name);
        sprite.setSize(tileSize, tileSize);
        sprite.setRotation(0);
        sprites.get(name).setSize(tileSize, tileSize);
        return sprites.get(name);
    }

    /**
     * TODO: finish when tiles are finished
     * @param tile
     * @return
     */
    public Sprite getTileSprite(ITile tile) {
        if (tile instanceof TileGear) {

        } else if (tile instanceof TileBlackhole) {
            return sprites.get("blackHole");
        } else if (tile instanceof TileAssemblyLine) {
            // rotation example:
            // sprite.setRotation(180);
        }

        return getSprite("empty");
    }

    public Sprite getRobotSprite(IRobot robot) {
        Sprite robotSprite = sprites.get("robot");
        robotSprite.setSize(tileSize, tileSize);
        return robotSprite;
    }

    public void setTileSize(int newSize) {
        this.tileSize = newSize;
    }

    public int getTileSize() {
        return this.tileSize;
    }
}
