package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        // cfg.title = "hello-world";
        // cfg.width = 480;
        // cfg.height = 320;
        // new LwjglApplication(new HelloWorld(), cfg);
        cfg.width = RobotDemo.WIDTH;
        cfg.height= RobotDemo.HEIGHT;
        cfg.title = RobotDemo.TITLE;
        new LwjglApplication(new RobotDemo(), cfg);
    }
}