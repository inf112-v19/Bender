package inf112.skeleton.app.utils.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.RobotDemo;

public class MenuState extends State {
    private CustomImageButton myButton;
    private ImageButton button;
    private Texture background;
    private BitmapFont font;
    private GlyphLayout layout;


    private boolean touched;
    private Stage stage;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        touched = false;

        initializeGraphics();
        makeButton();
        makeTitle();
    }

    public void initializeGraphics() {
        background = new Texture(Gdx.files.internal("res\\main_menu.png"));
    }

    private void makeTitle() {
        font = new BitmapFont(Gdx.files.internal("res\\font\\font.fnt"), Gdx.files.internal("res\\font\\font.png"), false);
        String text = "R O B O R A L L Y";
        font.getData().setScale(2f, 2f);
        font.setColor(90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
        layout = new GlyphLayout(font, text);
    }

    private void makeButton() {
        int y = (RobotDemo.HEIGHT / 2) - 50;
        int x = (RobotDemo.WIDTH / 2) - 100;
        myButton = new CustomImageButton("New Game Button.png", "New Game Button Pressed.png", x, y, 150, 100);


        button = myButton.getButton();
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touched = true;
            }
        });

        stage.addActor(button);
    }


    @Override
    public void handleInput() {
        if (touched) {
            gsm.set(new RoundState(gsm));
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
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, RobotDemo.WIDTH, RobotDemo.HEIGHT);
        font.draw(stage.getBatch(), layout, RobotDemo.WIDTH / 2 - (layout.width / 2), RobotDemo.HEIGHT - RobotDemo.HEIGHT / 4);
        stage.getBatch().end();
        stage.draw();

    }

    @Override
    public void dispose() {
        background.dispose();
        myButton.getTexture().dispose();
        font.dispose();
    }
}
