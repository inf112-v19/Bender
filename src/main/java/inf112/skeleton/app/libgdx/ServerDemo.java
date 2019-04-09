package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import inf112.skeleton.app.core.board.IBoard;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.interfaces.IAction;
import inf112.skeleton.app.libgdx.states.GameStateManager;
import inf112.skeleton.app.libgdx.states.MainMenuState;
import inf112.skeleton.app.server.API;
import inf112.skeleton.app.server.RemoteServerHandler;

import java.util.List;
import java.util.Queue;

public class ServerDemo extends ApplicationAdapter {

    private SpriteBatch batch;
    private GameStateManager gsm = new GameStateManager();
    private int width = 1280;
    private int height = 720;
    private String title = "Server Demo";
    private API serverHandler = new RemoteServerHandler(new handler());

    public ServerDemo(int width, int height, String title) throws Exception {
        this.width = width;
        this.height = height;
        this.title = title;
        serverHandler.connect();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        gsm.push(new MainMenuState(gsm, serverHandler, getWidth(), getHeight()));

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // wipes the screen clear
        gsm.update(Gdx.graphics.getDeltaTime()); //Difference between the render times
        gsm.render(batch);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public String getTitle() {
        return this.title;
    }

    private class handler implements IAction {

        @Override
        public void handleERROR(String message) {

        }

        @Override
        public void handleCards(List<IProgramCard> cards) {

        }

        @Override
        public void handleMoves(Queue<List<Move>> moves) {

        }

        @Override
        public void handleWARNING(String message) {

        }

        @Override
        public void handleINFO(String message) {

        }

        @Override
        public void handleBoard(IBoard board) {

        }

        @Override
        public void handleROOM(String roomId) {
            System.out.println("ROOMID: " + roomId);
        }
    }
}
