package inf112.skeleton.app.libgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.ITile;
import inf112.skeleton.app.core.tiles.TileAssemblyLine;
import inf112.skeleton.app.core.tiles.TileAssemblyLineMerge;
import inf112.skeleton.app.core.tiles.TileAssemblyLineTurn;
import inf112.skeleton.app.core.tiles.TileBlackhole;
import inf112.skeleton.app.core.tiles.TileGear;
import inf112.skeleton.app.core.enums.Direction;

import java.util.HashMap;

public class SpriteLoader {

    private HashMap<String, Sprite> sprites;

    private int tileSize = 96;

    private BitmapFont font;
    private BitmapFontCache bfc;

    public SpriteLoader() {
        initializeSprites();
        initializeFonts();
    }

    public SpriteLoader(int tileSize) {
        this.tileSize = tileSize;
        initializeSprites();
        initializeFonts();
    }

    private void initializeFonts() {
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false);
        bfc = new BitmapFontCache(font);
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public BitmapFontCache getFontCache() {
        return this.bfc;
    }

    private void initializeSprites() {
        sprites = new HashMap<>();
        Texture tiles = new Texture(Gdx.files.internal("tiles/tiles.png"));
        int tileSize = 96;

        Sprite empty = new Sprite(new TextureRegion(tiles, 4 * tileSize, 0 * tileSize, tileSize, tileSize));
        Sprite blackHole = new Sprite(new TextureRegion(tiles, 5 * tileSize, 0 * tileSize, tileSize, tileSize));

        Sprite assemblyForward = new Sprite(new TextureRegion(tiles, 0 * tileSize, 6 * tileSize, tileSize, tileSize));
        Sprite assemblyTurnLeft = new Sprite(new TextureRegion(tiles, 1 * tileSize, 4 * tileSize, tileSize, tileSize));
        Sprite assemblyTurnRight = new Sprite(new TextureRegion(tiles, 2 * tileSize, 4 * tileSize, tileSize, tileSize));

        Sprite fastAssemblyForward = new Sprite(new TextureRegion(tiles, 4 * tileSize, 1 * tileSize, tileSize, tileSize));
        Sprite fastAssemblyTurnLeft = new Sprite(new TextureRegion(tiles, 1 * tileSize, 2 * tileSize, tileSize, tileSize));
        Sprite fastAssemblyTurnRight = new Sprite(new TextureRegion(tiles, 2 * tileSize, 2 * tileSize, tileSize, tileSize));

        //Sprite assemblyFromRight = new Sprite(new TextureRegion(tiles, 0 * tileSize, 8 * tileSize, tileSize, tileSize)); // rotate
        //Sprite assemblyFromLeft = new Sprite(new TextureRegion(tiles, 0 * tileSize, 7 * tileSize, tileSize, tileSize)); // rotate
        Sprite assemblyMerge = new Sprite(new TextureRegion(tiles, 4 * tileSize, 8 * tileSize, tileSize, tileSize));

        //Sprite fastAssemblyFromRight = new Sprite(new TextureRegion(tiles, 4 * tileSize, 9 * tileSize, tileSize, tileSize)); // rotate
        //Sprite fastAssemblyFromLeft = new Sprite(new TextureRegion(tiles, 0 * tileSize, 9 * tileSize, tileSize, tileSize)); // rotate
        Sprite fastAssemblyMerge = new Sprite(new TextureRegion(tiles, 3 * tileSize, 10 * tileSize, tileSize, tileSize));

        Sprite rotateLeft = new Sprite(new TextureRegion(tiles, 4 * tileSize, 6 * tileSize, tileSize, tileSize));
        Sprite rotateRight = new Sprite(new TextureRegion(tiles, 5 * tileSize, 0 * tileSize, tileSize, tileSize));
        Sprite wall = new Sprite(new TextureRegion(tiles, 4 * tileSize, 3 * tileSize, tileSize, tileSize)); // rotate

        sprites.put("empty", empty);
        sprites.put("blackHole", blackHole);

        sprites.put("assemblyForward", assemblyForward);
        sprites.put("assemblyTurnLeft", assemblyTurnLeft);
        sprites.put("assemblyTurnRight", assemblyTurnRight);

        sprites.put("fastAssemblyForward", fastAssemblyForward);
        sprites.put("fastAssemblyTurnLeft", fastAssemblyTurnLeft);
        sprites.put("fastAssemblyTurnRight", fastAssemblyTurnRight);

        //sprites.put("assemblyFromRight", assemblyFromRight);
        //sprites.put("assemblyFromLeft", assemblyFromLeft);
        sprites.put("assemblyMerge", assemblyMerge);

        //sprites.put("fastAssemblyFromRight", fastAssemblyFromRight);
        //sprites.put("fastAssemblyFromLeft", fastAssemblyFromLeft);
        sprites.put("fastAssemblyMerge", fastAssemblyMerge);

        sprites.put("rotateLeft", rotateLeft);
        sprites.put("rotateRight", rotateRight);
        sprites.put("wall", wall);

        Sprite robot = new Sprite(new Texture(Gdx.files.internal("tiles/robot.png")));
        sprites.put("robot", robot);
    }

    public void dispose() {
        for (Sprite sprite : sprites.values()) {
            sprite.getTexture().dispose();
        }
    }

    private Sprite getSprite(String name) {
        return sprites.get(name);
    }

    private Sprite getSprite(String name, Direction dir) {
        Sprite sprite = sprites.get(name);
        switch (dir) {
            case NORTH:
                sprite.setRotation(0);
                break;

            case EAST:
                sprite.setRotation(90);
                break;

            case SOUTH:
                sprite.setRotation(180);
                break;

            case WEST:
                sprite.setRotation(270);
                break;

            default:
                break;
        }
        return sprite;
    }

    public void drawTile(SpriteBatch sb, ITile tile, int x, int y) {
        Sprite sprite = getTileSprite(tile);
        sprite.setBounds(x, y, this.tileSize, this.tileSize);
        sprite.setSize(this.tileSize, this.tileSize);
        sprite.draw(sb);
    }

    public void drawRobot(SpriteBatch sb, IRobot robot, float x, float y) {
        Sprite sprite = getRobotSprite(robot);
        sprite.setBounds(x, y, this.tileSize, this.tileSize);
        sprite.setSize(this.tileSize, this.tileSize);
        sprite.draw(sb);
    }

    /**
     * Method for getting a sprite based on the tile type
     *
     * @param tile
     * @return
     */
    public Sprite getTileSprite(ITile tile) {
        if(tile instanceof TileAssemblyLine) {

            TileAssemblyLine tileAssemblyLine = (TileAssemblyLine) tile;
            boolean isExpress = tileAssemblyLine.getExpress();

            if(tileAssemblyLine instanceof TileAssemblyLineMerge && isExpress) {
                return getSprite("fastAssemblyMerge", tileAssemblyLine.getDirection());
            }else if(tileAssemblyLine instanceof TileAssemblyLineMerge) {
                return getSprite("assemblyMerge", tileAssemblyLine.getDirection());
            }

            else if(tileAssemblyLine instanceof TileAssemblyLineTurn && isExpress) {
                switch(((TileAssemblyLineTurn) tileAssemblyLine).getTurnDir()) {
                    case LEFT:
                        return getSprite("fastAssemblyTurnLeft", tileAssemblyLine.getDirection());

                    case RIGHT:
                        return getSprite("fastAssemblyTurnRight", tileAssemblyLine.getDirection());
                }
            }else if(tileAssemblyLine instanceof TileAssemblyLineTurn) {
                switch(((TileAssemblyLineTurn) tileAssemblyLine).getTurnDir()) {
                    case LEFT:
                        return getSprite("assemblyTurnLeft", tileAssemblyLine.getDirection());

                    case RIGHT:
                        return getSprite("assemblyTurnRight", tileAssemblyLine.getDirection());
                }
            }

            else if(isExpress) {
                return getSprite("fastAssemblyForward", tileAssemblyLine.getDirection());
            }else{
                return getSprite("assemblyForward", tileAssemblyLine.getDirection());
            }

        } else if (tile instanceof TileGear) {

            TileGear tileGear = (TileGear) tile;
            switch (tileGear.getAngle()) {
                case LEFT:
                    return getSprite("rotateLeft");
                case RIGHT:
                    return getSprite("rotateRight");
                case UTURN:
                    return getSprite("empty");
            }

        } else if (tile instanceof TileBlackhole) {
            return getSprite("blackHole");
        }

        return getSprite("empty");
    }

    public Sprite getRobotSprite(IRobot robot) {
        Sprite robotSprite = sprites.get("robot");
        robotSprite.setOrigin(tileSize / 2, tileSize / 2);
        robotSprite.setRotation(0);
        switch (robot.getDirection()) {
            case NORTH:
                robotSprite.rotate(180);
                break;
            case WEST:
                robotSprite.rotate(270);
                break;
            case EAST:
                robotSprite.rotate(90);
                break;
            default:
                break;
        }
        return robotSprite;
    }

    public void setTileSize(int newSize) {
        this.tileSize = newSize;
    }

    public int getTileSize() {
        return this.tileSize;
    }
}
