package inf112.skeleton.app.libgdx.states;

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
import inf112.skeleton.app.core.board.Board;

import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.libgdx.RoboRally;

import java.net.URISyntaxException;


public class MenuState extends State {

    private CustomImageButton[] customButton;
    private ImageButton[] button;
    private Texture background;
    private BitmapFont font;
    private GlyphLayout layout;

    private boolean startedGame;
    private boolean multiplayer;
    private boolean singleplayer;
    private Stage stage;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        startedGame = false;
        singleplayer = false;
        multiplayer = false;

        initializeGraphics();
        makeButton();
        makeTitle();
    }

    public void initializeGraphics() {
        background = new Texture(Gdx.files.internal("other/main_menu.png"));
        customButton = new CustomImageButton[3];
        button = new ImageButton[3];
    }

    private void makeTitle() {
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false);
        String text = "R O B O R A L L Y";
        font.getData().setScale(2f, 2f);
        font.setColor(90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
        layout = new GlyphLayout(font, text);
    }

    private void makeButton() {
        int y = (RoboRally.HEIGHT / 2) - 50;
        int x = (RoboRally.WIDTH / 2) - 100;
        customButton[0] = new CustomImageButton("buttons/new_game_button.png", "buttons/new_game_button_pressed.png", x, y, 150, 100);


        button[0] = customButton[0].getButton();
        button[0].addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                startedGame = true;
            }
        });

        stage.addActor(button[0]);
    }


    @Override
    public void handleInput() {
        if (startedGame) {
            customButton[0].getTexture().dispose();
            font.dispose();
            createChoiceButtons();
        }
        if (multiplayer || singleplayer) {
            Board board = new Board("test1", 10, 10);
            Player player = new Player("");
            board.addRobot(player.getRobot(), new Position(5, 5));
            try {
                gsm.set(new RoundState(gsm, board, player, singleplayer));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            dispose();
        }
    }

    private void createChoiceButtons() {
        int y = (RoboRally.HEIGHT / 2) - 50;
        int x = (RoboRally.WIDTH / 2) - 100;

        customButton[1] = new CustomImageButton("buttons/singleplayer.png", "buttons/singleplayer.png", x, y - 75/2, 150, 100);
        customButton[2] = new CustomImageButton("buttons/multiplayer.png", "buttons/multiplayer.png", x, y + 75/2, 150, 100);
        button[1] = customButton[1].getButton();
        button[2] = customButton[2].getButton();
        button[1].addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                singleplayer = true;
            }
        });
        button[2].addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                multiplayer = true;
            }
        });
        stage.addActor(button[1]);
        stage.addActor(button[2]);

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        if (!startedGame)
            font.draw(stage.getBatch(), layout, RoboRally.WIDTH / 2 - (layout.width / 2), RoboRally.HEIGHT - RoboRally.HEIGHT / 4);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        customButton[0].getTexture().dispose();
        font.dispose();
    }
}
