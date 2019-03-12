package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.libgdx.ServerDemo;

public class MainMenuState extends State {

    private Stage stage;
    private Texture background;

    public MainMenuState(GameStateManager mng) {
        super(mng);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("other/main_menu.png"));
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, ServerDemo.WIDTH, ServerDemo.HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
