package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.server.API;

public class MainMenuState extends State {

    private int height;
    private int width;
    private int padding;

    private Stage stage;
    private Texture background;

    private CustomImageButton createButton;
    private static String createButtonTexture = "buttons/create_game_button.png";
    private static String createButtonPressedTexture = "buttons/create_game_button_pressed.png";
    private int createButtonX;
    private int createButtonY;
    private int createButtonWidth;
    private int createButtonHeight;

    private CustomImageButton joinButton;
    private static String joinButtonTexture = "buttons/join_game_button.png";
    private static String joinButtonPressedTexture = "buttons/join_game_button_pressed.png";
    private int joinButtonX;
    private int joinButtonY;
    private int joinButtonWidth;
    private int joinButtonHeight;

    public MainMenuState(GameStateManager mng, API serverHandler, int width, int height) {
        super(mng);
        this.height = height;
        this.width = width;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("other/main_menu.png"));
        calculateUiCoordinates();
        addUiElements();
    }

    private void calculateUiCoordinates() {
        padding = 20;

        joinButtonHeight = 100;
        joinButtonWidth = 200;
        joinButtonX = (getWidth() / 2) - (joinButtonWidth / 2);
        joinButtonY = getHeight() - (getHeight() / 4);

        createButtonHeight = 100;
        createButtonWidth = 254;
        createButtonX = (getWidth() / 2) - (createButtonWidth / 2);
        createButtonY = joinButtonY - joinButtonHeight - padding;
    }

    private void addUiElements() {
        addJoinButton();
        addCreateButton();
    }

    private void addJoinButton() {
        joinButton = new CustomImageButton(joinButtonTexture, joinButtonPressedTexture,
                joinButtonX, joinButtonY, joinButtonWidth, joinButtonHeight);
        joinButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // TODO : go to new page with input field
            }
        });
        stage.addActor(joinButton.getButton());
    }

    private void addCreateButton() {
        createButton = new CustomImageButton(createButtonTexture, createButtonPressedTexture,
                createButtonX, createButtonY, createButtonWidth, createButtonHeight);
        createButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // TODO : create room and go to page with room info
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
        stage.getBatch().draw(background, 0, 0, getWidth(), getHeight());
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

    private int getHeight() {
        return height;
    }

    private int getWidth() {
        return width;
    }
}
