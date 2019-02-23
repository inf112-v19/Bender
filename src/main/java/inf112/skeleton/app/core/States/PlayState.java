package inf112.skeleton.app.core.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.libgdx.RobotDemo;

public class PlayState extends State {

    private Texture tile;
    private Texture cardBackground;
    private Texture card;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        card = new Texture(Gdx.files.internal("res/cards/card.png"));
        cardBackground = new Texture(Gdx.files.internal("res/cards/card_background.png"));
        tile = new Texture(Gdx.files.internal("res/tiles/dungeon_tile.png"));
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
            sb.draw(card, x, 18);
    }

    @Override
    public void dispose() {

    }
}
