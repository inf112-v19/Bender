package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.libgdx.RobotDemo;
import inf112.skeleton.app.libgdx.VisualBoardLoader;

import java.io.IOException;

public class PhaseState extends State {
    private VisualBoardLoader visualBoardLoader;
    private Texture boardBackground;
    public Board board;
    private int currentPhaseNumber;
    private Player player;

    private static long time = 0; // TODO : refactor

    public PhaseState(GameStateManager gsm, Board board, Player player) {
        super(gsm);
        this.player = player;
        currentPhaseNumber = 0;
        this.board = board;
        visualBoardLoader = new VisualBoardLoader(board);
        initializeTextures();
    }

    private void initializeTextures() {
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_new.png"));
    }

    @Override
    protected void handleInput() {

    }

    // TODO : refactor
    boolean robotIsMoving;
    Position startPosition;
    Position endPosition;
    float progress;

    @Override
    public void update(float dt) {
        if (currentPhaseNumber == 5) {
            if (time++ < 20) return;
            try {
                visualBoardLoader.disposeTextures();
                gsm.pop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        if (robotIsMoving) {
            progress += 0.02;
            if (progress >= 1) {
                robotIsMoving = false;
                currentPhaseNumber++;
            }
            return;
        } else {
            moveRobot(player.getRobot());
        }
    }

    private void moveRobot(IRobot robot) {
        progress = 0;
        startPosition = board.getRobotPosition(robot);
        IProgramCard card = player.getRobot().drawCard();
        board.moveRobot(robot, card);
        System.out.println(player.getRobot().getDirection());
        endPosition = board.getRobotPosition(robot);
        robotIsMoving = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        sb.begin();
        sb.draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoard(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp);

        if (robotIsMoving) {
            visualBoardLoader.renderRobotSlowly(sb, player.getRobot(), RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp, startPosition, endPosition, progress);
        } else {
            visualBoardLoader.renderRobot(sb, player.getRobot(), RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp, visualBoardLoader.getRobotPos(), false);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
