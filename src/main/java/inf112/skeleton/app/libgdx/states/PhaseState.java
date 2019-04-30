package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.MoveEvent;
import inf112.skeleton.app.libgdx.RoboRally;
import inf112.skeleton.app.libgdx.utils.VisualBoardLoader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PhaseState extends State {

    public Board board;
    private Queue<List<Event>> robotEvents;

    private VisualBoardLoader visualBoardLoader;
    private long waitTimeAfterMoves;
    private boolean robotsAreMoving;
    private float progress;
    private static float movementSpeed = 0.03f;
    private Texture boardBackground;

    public PhaseState(GameStateManager gsm, Board board, Queue<List<Event>> robotMoves) {
        super(gsm);
        this.robotEvents = robotMoves;
        this.robotsAreMoving = false;
        this.progress = 0;
        this.board = board;
        this.waitTimeAfterMoves = 0;

        visualBoardLoader = new VisualBoardLoader(board);
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_new.png"));
    }

    @Override
    public void update(float dt) {
        if (robotEvents.isEmpty()) {
            if (waitTimeAfterMoves++ < 20) return;
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
                for (Event event : robotEvents.peek()) {
                    event.apply(board);
                }
                robotEvents.poll();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (!robotsAreMoving) {
            robotsAreMoving = true;
            progress = 0;
            update(0);
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        sb.begin();
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        int xStart = RoboRally.WIDTH / 2 - temp;
        int yStart = RoboRally.HEIGHT / 2 - temp;

        sb.draw(boardBackground, 0, 0);
        visualBoardLoader.renderBoard(sb, xStart, yStart);
        visualBoardLoader.renderRobots(sb, board, robotEvents.peek(), progress, xStart, yStart);
        sb.end();
    }
}
