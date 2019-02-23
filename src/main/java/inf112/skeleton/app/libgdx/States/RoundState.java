package inf112.skeleton.app.libgdx.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.ProgramCard;
import inf112.skeleton.app.core.cards.ProgramDeck;
import inf112.skeleton.app.libgdx.RobotDemo;

import java.util.ArrayDeque;

public class RoundState extends State {
    private ProgramDeck deck;
    private boolean confirmed;
    private Stage stage;
    private Texture tileTexture;
    private Texture cardBackground;
    private CustomImageButton[] cards;
    private IProgramCard[] availableRoundCard;
    private ArrayDeque<IProgramCard> chosenCards;

    private CustomImageButton confirm;
    private CustomImageButton reset;

    public RoundState(GameStateManager gsm) {
        super(gsm);
        chosenCards = new ArrayDeque();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        initializeTextures();
        makeCardButtons();
        makeConfirmationButtons();
        makeDeck();
    }

    public void initializeTextures() {
        cardBackground = new Texture(Gdx.files.internal("res/Card_background1.png"));
        tileTexture = new Texture(Gdx.files.internal("res/tiles/empty_tile.png"));
    }

    public void makeDeck() {
        deck = new ProgramDeck();
        this.drawForRound();
    }

    public void drawForRound() {
        availableRoundCard = new IProgramCard[9];
        for (int i = 0; i < 9; i++) {
            availableRoundCard[i] = deck.draw();
        }
    }

    @Override
    protected void handleInput() {
        if (chosenCards.size() == 5 && confirmed) {
            for (int i = 0; i < 5; i++) {
                System.out.println(chosenCards.pop().priority());
                gsm.set(new PhaseState(gsm));
            }
        }
    }

    public void makeConfirmationButtons() {
        confirm = new CustomImageButton("res/buttons/Confirm.png", "res/buttons/Confirm.png", RobotDemo.WIDTH - 250, RobotDemo.CARD_WIDTH / 2 + 50, 100, 50);
        reset = new CustomImageButton("res/buttons/Reset.png", "res/buttons/Reset.png", RobotDemo.WIDTH - 250, 30, 100, 50);
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
                chosenCards.clear();
                confirmed = false;
                return true;
            }
        });

        stage.addActor(confirm.getButton());
        stage.addActor(reset.getButton());
    }


    public void makeCardButtons() {
        cards = new CustomImageButton[9];

        for (int i = 0; i < 9; i++) {
            int x = RobotDemo.CARD_WIDTH * i + RobotDemo.CARD_WIDTH / 4;
            int y = 18;
            int width = RobotDemo.CARD_WIDTH - 30;
            int height = RobotDemo.CARD_HEIGHT - 60;
            cards[i] = new CustomImageButton("res/cards/card.png", "res/cards/card.png", x, y, width, height);

            final int finalI = i;
            cards[i].getButton().addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (!chosenCards.contains(availableRoundCard[finalI]) && chosenCards.size() < 5)
                        chosenCards.add((ProgramCard) availableRoundCard[finalI]);

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

    public void renderTiles(SpriteBatch sb) { // Eventually should be implemented in board class, based on tile positions // board.draw();?
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
