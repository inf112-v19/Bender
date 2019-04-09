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
import inf112.skeleton.app.libgdx.Move;
import inf112.skeleton.app.libgdx.RobotDemo;
import inf112.skeleton.app.libgdx.VisualBoardLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class PhaseState extends State {

    private VisualBoardLoader visualBoardLoader;
    private Texture boardBackground;
    public Board board;
    private int currentPhaseNumber;
    private Player player;

    private static long time = 0; // TODO : refactor





    private HashMap<IRobot, Position> robotPositions;
    private Queue<List<Move>> robotMoves;
    private boolean robotsAreMoving;
    private float progress;
    private static float movementSpeed = 0.02f;






    public PhaseState(GameStateManager gsm, Board board, Queue<List<Move>> robotMoves) {
        super(gsm);
        this.robotMoves = robotMoves;
        this.robotsAreMoving = false;
        this.progress = 0;
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

    @Override
    public void update(float dt) {

        // new logic
        if (robotMoves.isEmpty()) {
            if (time++ < 20) return;
            visualBoardLoader.disposeTextures();
            gsm.pop();
        } else if (!robotsAreMoving) {
            robotsAreMoving = true;
            progress = 0;
        }

        if (robotsAreMoving) {
            progress += movementSpeed;
            if (progress >= 1) {
                robotsAreMoving = false;
                for (Move move : robotMoves.peek()) {
                    board.moveRobotToNewTile(move.getFrom(), move.getEnd());
                }
                robotMoves.poll();
            }
        }
        // =========







        /*
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
        */

        /*
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
        */
    }

    /*
    private void moveRobot(IRobot robot) {
        progress = 0;
        startPosition = board.getRobotPosition(robot);
        IProgramCard card = player.getRobot().drawCard();
        board.moveRobot(robot, card);
        System.out.println(player.getRobot().getDirection());
        endPosition = board.getRobotPosition(robot);
        robotIsMoving = true;
    }
    */

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        sb.begin();
        sb.draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;

        int xStart = RobotDemo.WIDTH / 2 - temp;
        int yStart = RobotDemo.HEIGHT / 2 - temp;

        visualBoardLoader.renderBoard(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp);

        /*
        if (robotIsMoving) {
            visualBoardLoader.renderRobotSlowly(sb, player.getRobot(), RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp, startPosition, endPosition, progress);
        } else {
            visualBoardLoader.renderRobot(sb, player.getRobot(), RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp, visualBoardLoader.getRobotPos(), false);
        }
        */

        visualBoardLoader.renderRobots(sb, board, robotMoves.peek(), progress, xStart, yStart);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
