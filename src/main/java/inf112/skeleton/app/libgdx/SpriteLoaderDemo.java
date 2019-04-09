package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.libgdx.states.GameStateManager;
import inf112.skeleton.app.libgdx.states.State;
import inf112.skeleton.app.libgdx.utils.SpriteLoader;

public class SpriteLoaderDemo extends ApplicationAdapter {

    private SpriteBatch batch;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private GameStateManager gsm = new GameStateManager();

    @Override
    public void create() {
        SpriteLoader spriteLoader = new SpriteLoader();

        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        gsm.push(new SpriteLoaderDemoState(gsm, spriteLoader));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // wipes the screen clear
        gsm.update(Gdx.graphics.getDeltaTime()); //Difference between the render times
        gsm.render(batch);
    }

    public static void main(String[] args) throws Exception {

        SpriteLoaderDemo demo = new SpriteLoaderDemo();
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = WIDTH;
        cfg.height= HEIGHT;

        new LwjglApplication(demo, cfg);
    }

    private class SpriteLoaderDemoState extends State {

        public void reInitialize() {

        }

        private SpriteLoader spriteLoader;

        public int robotX = 0;
        public int robotY = 0;

        public int width = 10;
        public int height = 10;

        public SpriteLoaderDemoState(GameStateManager gsm, SpriteLoader spriteLoader) {
            super(gsm);
            this.spriteLoader = spriteLoader;
        }


        @Override
        public void update(float dt) {

        }

        @Override
        public void render(SpriteBatch sb) {
            int speed = 10;
            if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
                robotX -= speed;
            if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
                robotX += speed;
            if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
                robotY += speed;
            if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
                robotY -= speed;

            sb.begin();
            Sprite empty = spriteLoader.getTileSprite(null);
            empty.setScale(0.5f);
            int tileSize = spriteLoader.getTileSize();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    sb.draw(empty, tileSize * i, tileSize * j, tileSize, tileSize);
                }
            }

            Sprite robot = spriteLoader.getRobotSprite(null);
            sb.draw(robot, robotX, robotY, tileSize, tileSize);
            sb.end();
        }

        @Override
        public void dispose() {

        }
    }
}
