package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
        states.peek().reInitialize();
    }

    public void set(State state) {
        states.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }
    //SpriteBatch renders everything to the screen
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
