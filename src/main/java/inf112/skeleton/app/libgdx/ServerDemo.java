package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.interfaces.IAction;
import inf112.skeleton.app.libgdx.states.GameStateManager;
import inf112.skeleton.app.libgdx.states.MainMenuState;
import inf112.skeleton.app.server.API;
import inf112.skeleton.app.server.RemoteServerHandler;

import java.net.URISyntaxException;

public class ServerDemo extends ApplicationAdapter {

    private SpriteBatch batch;
    private GameStateManager gsm = new GameStateManager();
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Server Demo";
    private API serverHandler = new RemoteServerHandler(new handler());

    public ServerDemo() throws Exception {
        serverHandler.connect();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        gsm.push(new MainMenuState(gsm, serverHandler));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // wipes the screen clear
        gsm.update(Gdx.graphics.getDeltaTime()); //Difference between the render times
        gsm.render(batch);
    }

    private class handler implements IAction {

        @Override
        public void handleERROR(String message) {

        }

        @Override
        public void handleWARNING(String message) {

        }

        @Override
        public void handleINFO(String message) {

        }

        @Override
        public void handleBOARD(IBoard board) {

        }

        @Override
        public void handleCardDraw(IProgramCard card) {

        }

        @Override
        public void handleROOM(String roomId) {
            System.out.println("ROOMID: " + roomId);
        }
    }
}
