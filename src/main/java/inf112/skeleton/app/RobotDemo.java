package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.States.GameStateManager;
import inf112.skeleton.app.States.MenuState;

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
    private GameStateManager gsm = new GameStateManager();

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // wipes the screen clear
        gsm.update(Gdx.graphics.getDeltaTime()); //Difference between the render times
        gsm.render(batch);
    }
}