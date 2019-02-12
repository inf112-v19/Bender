package inf112.skeleton.app.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.RobotDemo;

public class PlayState extends State {
    private boolean confirmed;
    private int selectedCardCount;
    private Stage stage;
    private Texture tile;
    private Texture cardBackground;
    private Texture cardTexture;
    private ImageButton cards[];
    private Drawable cardImage; // TODO: assign a unique drawable to the card class ? ==> deal them to a player  ==> only need a player in this class

    //TODO: make button a genereic method/class
    public PlayState(GameStateManager gsm) {
        super(gsm);
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        cardTexture = new Texture(Gdx.files.internal("res\\Card.png"));
        cardBackground = new Texture(Gdx.files.internal("res\\card_background.PNG"));
        tile = new Texture(Gdx.files.internal("res\\dungeon_tile.png"));

        for (int i = 0; i < 5; i++) {
            cards[i] = new ImageButton(cardImage);
            cards[i].setSize(RobotDemo.CARD_WIDTH, RobotDemo.CARD_WIDTH);
            cards[i].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(cardTexture));
            cards[i].getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(cardTexture));
            cards[i].addListener(new InputListener() {
                @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                            return true;


                }
            });
        }

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
        this.renderTiles(sb);
        this.renderCards(sb);
        sb.end();
    }

    public void renderTiles(SpriteBatch sb) {
        sb.draw(cardBackground, 0, 0);
        for (int i = 0; i < RobotDemo.WIDTH; i += 32)
            for (int j = RobotDemo.CARD_HEIGHT; j < RobotDemo.HEIGHT; j += 32)
                sb.draw(tile, i, j);
    }

    public void renderCards(SpriteBatch sb) {
        for (int x = 0; x < RobotDemo.WIDTH / 3; x += RobotDemo.CARD_WIDTH + RobotDemo.CARD_WIDTH / 4)
            sb.draw(cardTexture, x, 18);
    }

    @Override
    public void dispose() {

    }
}
