package inf112.skeleton.app.libgdx.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.libgdx.RobotDemo;

public class MenuState extends State {
    private Texture background;
    private Texture playButtonTexture;
    private Texture playButtonTexturePressed;
    private Drawable playButtonImage;
    private ImageButton playButton;
    private BitmapFont font;
    private final String FONT_CHARACTERS = "ROBORALLY";

    private boolean touched;
    private Stage stage;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        touched = false;

        playButtonTexturePressed = new Texture(Gdx.files.internal("src/main/resources/buttons/new_game_button_pressed.png"));
        background = new Texture(Gdx.files.internal("src/main/resources/other/main_menu.png"));
        playButtonTexture = new Texture(Gdx.files.internal("src/main/resources/buttons/new_game_button.png"));


        stage = new Stage(new ScreenViewport());
        makeButton();
    }

    private void makeButton() {
        Gdx.input.setInputProcessor(stage);

        playButton = new ImageButton(playButtonImage);
        playButton.setSize(150, 100);
        playButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(playButtonTexture));
        playButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(playButtonTexturePressed));
        playButton.setPosition((RobotDemo.WIDTH / 2) - (playButtonTexture.getWidth() / 2), (RobotDemo.HEIGHT / 2) - (playButtonTexture.getHeight() / 2));
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touched = true;
                return true;
            }
        });
        stage.addActor(playButton);
    }


    @Override
    public void handleInput() {
        if (touched) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.act();

        //Renders the background
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, RobotDemo.WIDTH, RobotDemo.HEIGHT);
        stage.getBatch().end();

        stage.draw();

    }

    @Override
    public void dispose() {
        background.dispose();
        playButtonTexture.dispose();
    }
}
