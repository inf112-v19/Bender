package inf112.skeleton.app.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.RobotDemo;

public class MenuState extends State {
    private Texture background;
    private Texture playButtonTexture;
    private Drawable playButtonImage;
    private InputListener input;
    private ImageButton playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture(Gdx.files.internal("C:\\Users\\Asus\\INF112\\Bender\\src\\main\\java\\inf112\\skeleton\\app\\Textures\\main_menu.png"));
        playButtonTexture = new Texture(Gdx.files.internal("C:\\Users\\Asus\\INF112\\Bender\\src\\main\\java\\inf112\\skeleton\\app\\Textures\\New Game Button.png"));

        playButtonImage = new TextureRegionDrawable(new TextureRegion(playButtonTexture));
        playButton = new ImageButton(playButtonImage);
        playButton.setSize(150,100);
        playButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(playButtonTexture));
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, RobotDemo.WIDTH, RobotDemo.HEIGHT);
        sb.draw(playButtonTexture, (RobotDemo.WIDTH / 2) - (playButtonTexture.getWidth() / 2), (RobotDemo.HEIGHT / 2) - (playButtonTexture.getHeight() / 2));
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playButtonTexture.dispose();
    }
}
