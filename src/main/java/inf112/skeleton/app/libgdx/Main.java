package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = RoboRally.WIDTH;
        cfg.height= RoboRally.HEIGHT;
        cfg.title = RoboRally.TITLE;
        new LwjglApplication(new RoboRally(), cfg);
    }
}