package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.libgdx.RobotDemo;
import inf112.skeleton.app.libgdx.VisualBoardLoader;

import java.io.IOException;

public class PhaseState extends State {
    private VisualBoardLoader visualBoardLoader;
    private Texture boardBackground;
    private Board board;
    private int currentPhaseNumber;
    private int initialPhaseNumber;
    private Player player;

    public PhaseState(GameStateManager gsm, Board board, Player player) throws IOException {
        super(gsm);
        this.player = player;
        currentPhaseNumber = 1;
        initialPhaseNumber = 1;
        this.board = board;
        visualBoardLoader = new VisualBoardLoader(board);
//        visualBoardLoader = new VisualBoardLoader("src/main/resources/boards/sampleboard1.txt");
        initializeTextures();
    }

    public PhaseState(GameStateManager gsm, Board board, Player player, int phaseNumber) throws IOException {
        super(gsm);
        phaseNumber++;
        this.player = player;
        this.board = board;
        visualBoardLoader = new VisualBoardLoader(board);
//        visualBoardLoader = new VisualBoardLoader("src/main/resources/boards/sampleboard1.txt");
        initializeTextures();
    }

    private void initializeTextures() {
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_new.png"));
    }

    private void moveRobot() {
        board.stepRobots();
    }

    private void drawRobot(SpriteBatch sb) {

    }

    @Override
    protected void handleInput() {

        //TODO

    }

    @Override
    public void update(float dt) {
        while (player.getRobot().getNumberOfCards() > 0) {
            IProgramCard card = player.getRobot().drawCard();
            board.moveRobot(player.getRobot(), card);
            System.out.println("moved " + card.toString());
            currentPhaseNumber++;
        }
        try {
            gsm.set(new RoundState(gsm, board, player));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoard(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp);
        visualBoardLoader.renderRobot(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp);
        sb.end();
    }

    @Override
    public void dispose() {
        //TODO
    }
}
