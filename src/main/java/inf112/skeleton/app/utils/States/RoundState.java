package inf112.skeleton.app.utils.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.RobotDemo;

import java.util.ArrayDeque;

public class RoundState extends State {
    private ArrayDeque order;
    private boolean confirmed;
    private Stage stage;
    private Texture tileTexture;
    private Texture cardBackground;
    private CustomImageButton[] cards;

    private CustomImageButton confirm;
    private CustomImageButton reset;

    public RoundState(GameStateManager gsm) {
        super(gsm);
        order = new ArrayDeque();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        initializeTextures();
        makeCardButtons();
        makeConfirmationButtons();
    }

    public void initializeTextures() {
        cardBackground = new Texture(Gdx.files.internal("res\\Card_background1.png"));
        tileTexture = new Texture(Gdx.files.internal("res\\dungeon_tile.png"));
    }

    @Override
    protected void handleInput() {
        if (order.size() == 5 && confirmed) {
            for (int i = 0; i < 5; i++) {
                System.out.println(order.pop());
                gsm.set(new PhaseState(gsm));
            }
        }
    }

    public void makeConfirmationButtons() {
        confirm = new CustomImageButton("Confirm.png", "Confirm.png", RobotDemo.WIDTH - 300, RobotDemo.CARD_WIDTH / 2, 100, 50);
        reset = new CustomImageButton("Reset.png", "Reset.png", RobotDemo.WIDTH - 300, 30, 100, 50);
        confirm.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                confirmed = true;
                return true;
            }
        });

        reset.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                order.clear();
                confirmed = false;
                return true;
            }
        });

        stage.addActor(confirm.getButton());
        stage.addActor(reset.getButton());
    }


    public void makeCardButtons() {
        cards = new CustomImageButton[5];

        for (int i = 0; i < 5; i++) {
            int x = RobotDemo.CARD_WIDTH * i + RobotDemo.CARD_WIDTH / 4;
            int y = 18;
            int width = RobotDemo.CARD_WIDTH-30;
            int height = RobotDemo.CARD_HEIGHT-60;
            cards[i] = new CustomImageButton("card.png", "card.png", x, y, width, height);

            final int finalI = i;
            cards[i].getButton().addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (!order.contains(finalI)) {
                        order.add(finalI);
                    }
                    return true;

                }
            });
            stage.addActor(cards[i].getButton());
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.act();
        stage.getBatch().begin();
        this.renderTiles(sb);
        stage.getBatch().end();
        stage.draw();
    }

    public void renderTiles(SpriteBatch sb) {
        stage.getBatch().draw(cardBackground, 0, 0);
        for (int i = 0; i < RobotDemo.WIDTH; i += 32)
            for (int j = 200; j < RobotDemo.HEIGHT; j += 32)
                stage.getBatch().draw(tileTexture, i, j);
    }

    @Override
    public void dispose() {
        confirm.getTexture().dispose();
        reset.getTexture().dispose();
        cardBackground.dispose();
        tileTexture.dispose();

    }
}
