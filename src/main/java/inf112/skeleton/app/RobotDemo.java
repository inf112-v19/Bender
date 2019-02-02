package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RobotDemo extends ApplicationAdapter {
    SpriteBatch batch;
    Texture tile;
    Texture cardBackground;
    Texture card;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int CARD_WIDTH = 84;
    public static final int CARD_HEIGHT = 150;
    public static final String TITLE = "RoboRally";

    @Override
    public void create() {
        batch = new SpriteBatch();
        card = new Texture(Gdx.files.internal("res/card.png"));
        cardBackground = new Texture(Gdx.files.internal("res/card_background.png"));
        tile = new Texture(Gdx.files.internal("res/dungeon_tile.png"));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(cardBackground, 0, 0);
        //loop to generate tiles, a single tile is 32x32 pixels
        for (int i = 0; i < WIDTH; i += 32)
            for (int j = CARD_HEIGHT; j < HEIGHT; j += 32)
                batch.draw(tile, i, j);

        // loop to generate cards, TODO: limit cards to 5
        for (int x = 0; x < WIDTH; x += CARD_WIDTH + CARD_WIDTH / 4)
            batch.draw(card, x, 18);
        batch.end();
    }
}