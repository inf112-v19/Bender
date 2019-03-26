package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
    public Board board;
//    private int currentPhaseNumber; not needed until some action needs to occur inbetween the phases.
//    private int initialPhaseNumber;
    private Player player;

    public PhaseState(GameStateManager gsm, Board board, Player player) throws IOException {
        super(gsm);
        this.player = player;
//        currentPhaseNumber = 1;
//        initialPhaseNumber = 1;
        this.board = board;
        visualBoardLoader = new VisualBoardLoader(board);
        initializeTextures();
    }

    //Redundant right now, will be needed for handling animations and actions inbetween phases further on.
//    public PhaseState(GameStateManager gsm, Board board, Player player, int phaseNumber) throws IOException {
//        super(gsm);
//        phaseNumber++;
//        this.player = player;
//        this.board = board;
//        visualBoardLoader = new VisualBoardLoader(board);
//        initializeTextures();
//    }

    private void initializeTextures() {
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_new.png"));
    }

    @Override
    protected void handleInput() {

        //TODO

    }

    @Override
    public void update(float dt) {
        while (player.getRobot().getNumberOfCards() > 0) {
            visualBoardLoader.updateBoard(board);
            IProgramCard card = player.getRobot().drawCard();
            System.out.println(card);
            board.moveRobot(player.getRobot(), card);
            System.out.println(player.getRobot().getDirection());
            System.out.println(visualBoardLoader.getRobotPos().getX());
            System.out.println(visualBoardLoader.getRobotPos().getY());
            visualBoardLoader.updateBoard(this.board);
//            currentPhaseNumber++;
        }
        try {
            visualBoardLoader.disposeTextures();
            gsm.set(new RoundState(gsm, board, player, visualBoardLoader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        sb.begin();
        sb.draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoard(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp);
        visualBoardLoader.renderRobot(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp, visualBoardLoader.getRobotPos(), false);
        sb.end();
    }

    @Override
    public void dispose() {
        //TODO
    }
}
