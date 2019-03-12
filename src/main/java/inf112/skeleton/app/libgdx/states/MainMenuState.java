package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.libgdx.ServerDemo;
import inf112.skeleton.app.server.API;
import inf112.skeleton.app.server.RemoteServerHandler;

public class MainMenuState extends State {

    private Stage stage;
    private Texture background;
    private CustomImageButton joinButton;
    private CustomImageButton createButton;

    private String ip = "localhost";

    public MainMenuState(GameStateManager mng, API serverHandler) {
        super(mng);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("other/main_menu.png"));

        joinButton = new CustomImageButton(
                "buttons/join_game_button.png",
                "buttons/join_game_button_pressed.png",
                ServerDemo.WIDTH / 2 - 100, 250, 200, 100);
        joinButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // serverHandler.joinRoom("roomid");
                // TODO : go to new page with input field
            }
        });
        stage.addActor(joinButton.getButton());

        createButton = new CustomImageButton(
                        "buttons/create_game_button.png",
                        "buttons/create_game_button_pressed.png",
                        ServerDemo.WIDTH / 2 - 127, 400, 254, 100);
        createButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                serverHandler.createRoom();
            }
        });
        stage.addActor(createButton.getButton());
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
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        background.dispose();
        joinButton.getTexture().dispose();
        createButton.getTexture().dispose();
    }
}
