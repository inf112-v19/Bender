package inf112.skeleton.app.libgdx.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.libgdx.VisualBoardLoader;

import java.io.IOException;

public class PhaseState extends State{
    private VisualBoardLoader visualBoardLoader;
    protected PhaseState(GameStateManager gsm) throws IOException {
        super(gsm);
        visualBoardLoader = new VisualBoardLoader("res/boards/sampleboard1.txt");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        visualBoardLoader.renderBoard(sb);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
