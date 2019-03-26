package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Main {

    public static void main(String[] args) throws Exception {
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