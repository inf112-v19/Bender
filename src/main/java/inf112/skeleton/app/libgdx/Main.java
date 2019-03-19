package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) throws Exception {
        ServerDemo demo = new ServerDemo(1280, 720, "server demo");
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = demo.getWidth();
        cfg.height= demo.getHeight();
        cfg.title = demo.getTitle();
        new LwjglApplication(demo, cfg);
    }
}