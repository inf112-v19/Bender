package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameRoomState extends State {
    private final Stage stage;
    private TextField serverRoom;
    private ImageButton JoinButton;
    private ImageButton CreateButton;

    protected GameRoomState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        initializeGraphics();
        makeButtons();
        makeTextField();
    }

    private void makeTextField() {

    }

    private void makeButtons() {
    }

    private void initializeGraphics() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }
}
