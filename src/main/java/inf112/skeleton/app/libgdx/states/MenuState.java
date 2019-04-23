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
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.Robot;
import inf112.skeleton.app.libgdx.RoboRally;

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
        background = new Texture(Gdx.files.internal("other/main_menu.png"));
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
        myButton = new CustomImageButton("buttons/new_game_button.png", "buttons/new_game_button_pressed.png", x, y, 150, 100);


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
                Board board = new Board("empty", 10, 10);
//                Robot robot = new Robot(Direction.WEST);
//                robot.setDirection(Direction.NORTH);
//            System.out.println(robot.getDirection());
                Player player = new Player("test");
//                player.
                board.addRobot(player.getRobot(), new Position(5,5));
                gsm.set(new RoundState(gsm, board, player));
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
        stage.getBatch().draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        font.draw(stage.getBatch(), layout, RoboRally.WIDTH / 2 - (layout.width / 2), RoboRally.HEIGHT - RoboRally.HEIGHT / 4);
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
