package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.libgdx.RoboRally;

import java.net.URISyntaxException;

public class ChooseRoomState extends State {
    private final Stage stage;
    private TextField serverRoom;
    private CustomImageButton joinButton;
    private CustomImageButton createButton;
    private ImageButton joinImageButton;
    private ImageButton createImageButton;
    private Texture background;
    private Status choice;

    protected ChooseRoomState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        initializeGraphics();
        makeButtons();
        makeTextField();
    }

    private void makeTextField() {
        int x = RoboRally.WIDTH/2 - 264;
        int y = RoboRally.HEIGHT/2 - 100;

        Skin skin = new Skin(Gdx.files.internal("skins/textfield-roomid/textfield-roomid.json"));
        serverRoom = new TextField("", skin);
        serverRoom.setPosition(x, y);
        serverRoom.setSize(300, 100);

        choice = Status.NONE;

        stage.addActor(serverRoom);
    }

    private void makeButtons() {
        makeCreateServerButton();
        makeJoinServerButton();
    }

    private void makeCreateServerButton() {
        int y = (RoboRally.HEIGHT / 2) + 50;
        int x = (RoboRally.WIDTH / 2) - 264;
        createButton = new CustomImageButton("buttons/create_game_button.png",
                "buttons/create_game_button_pressed.png",
                x, y, 254, 100);

        createImageButton = createButton.getButton();
        createImageButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                choice = Status.CREATE;
            }
        });

        stage.addActor(createImageButton);
    }
    private void makeJoinServerButton() {
        int y = (RoboRally.HEIGHT / 2) + 50;
        int x = (RoboRally.WIDTH / 2);
        joinButton = new CustomImageButton("buttons/join_game_button.png",
                "buttons/join_game_button_pressed.png",
                x, y, 200, 100);


        joinImageButton = joinButton.getButton();
        joinImageButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                choice = Status.JOIN;
            }
        });

        stage.addActor(joinImageButton);
    }

    private void initializeGraphics() {
        background = new Texture(Gdx.files.internal("other/main_menu.png"));
    }

    @Override
    public void update(float dt) {
        switch (choice) {
            case CREATE:
                createGame();
                break;
            case JOIN:
                joinGame();
                break;
        }
    }

    private void joinGame() {
        //TODO: Make a new state with an overview of all players.
    }

    private void createGame() {
        Board testBoard = new Board("test1", 10, 10);
        Player testPlayer = new Player("petter");
        testBoard.addRobot(testPlayer.getRobot(), new Position(5, 5));

        try {
            gsm.set(new RoundState(gsm, testBoard, testPlayer, true));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    private enum Status {
        CREATE, JOIN, NONE
    }
}
