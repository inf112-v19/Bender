package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.libgdx.RobotDemo;
import inf112.skeleton.app.libgdx.VisualBoardLoader;

import java.io.IOException;

public class PhaseState extends State {
    private VisualBoardLoader visualBoardLoader;
    private Texture boardBackground;
    protected PhaseState(GameStateManager gsm, Board board) throws IOException {
        super(gsm);
        visualBoardLoader = new VisualBoardLoader(board);
//        visualBoardLoader = new VisualBoardLoader("src/main/resources/boards/sampleboard1.txt");
        initializeTextures();
    }

    private void initializeTextures() {
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_new.png"));
    }

    @Override
    protected void handleInput() {
        //TODO

    }

    @Override
    public void update(float dt) {
        //TODO
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoard(sb, RobotDemo.WIDTH / 2 - temp, RobotDemo.HEIGHT / 2 - temp);
        sb.end();
    }

    @Override
    public void dispose() {
        //TODO
    }
}
