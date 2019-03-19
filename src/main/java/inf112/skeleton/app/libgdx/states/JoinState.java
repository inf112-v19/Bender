package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.server.API;

public class JoinState extends State {

    private int width;
    private int height;
    private int padding;

    private Stage stage;
    private Texture background;

    private CustomImageButton joinButton;
    private static String joinButtonTexture = "buttons/join_game_button.png";
    private static String joinButtonPressedTexture = "buttons/join_game_button_pressed.png";
    private int joinButtonX;
    private int joinButtonY;
    private int joinButtonWidth;
    private int joinButtonHeight;

    private TextField roomIdInput;
    private int idInputX;
    private int idInputY;
    private int idInputWidth;
    private int idInputHeight;

    private Skin skin;

    public JoinState(GameStateManager gsm, API serverHandler, int width, int height) {
        super(gsm);
        this.width = width;
        this.height = height;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("other/main_menu.png"));
        TextureAtlas atlas;
        atlas = new TextureAtlas(Gdx.files.internal("atlas/craftacular-ui.json"));
        Skin skin = new Skin();
        skin.addRegions(atlas);
        this.skin = skin;




        this.calculateUiCoordinates();
        this.addUiElements();
    }

    private void calculateUiCoordinates() {
        padding = 20;

        joinButtonHeight = 100;
        joinButtonWidth = 200;
        joinButtonX = (getWidth() / 2) - (joinButtonWidth / 2);
        joinButtonY = getHeight() - (getHeight() / 4);

        idInputHeight = 100;
        idInputWidth = 200;
        idInputX = (getWidth() / 2) - (idInputWidth / 2);
        idInputY = joinButtonY - joinButtonHeight - padding;
    }

    private void addUiElements() {
        addJoinButton();
        addRoomIdInput();
    }

    private void addRoomIdInput() {
        roomIdInput = new TextField("RoomId", skin);
        roomIdInput.setX(idInputX);
        roomIdInput.setY(idInputY);
        roomIdInput.setWidth(idInputWidth);
        roomIdInput.setHeight(idInputHeight);
        stage.addActor(roomIdInput);
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
    }

    @Override
    protected void handleInput() {

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
