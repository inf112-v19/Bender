package inf112.skeleton.app.libgdx.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private boolean[] selectedCard;
    private ArrayDeque<IProgramCard> chosenCards;
    private BitmapFont[] font;
    private GlyphLayout[] visualCardSequencing;
    private int latestSelectedCardPos;


    private CustomImageButton confirm;
    private CustomImageButton reset;

    public RoundState(GameStateManager gsm) {
        super(gsm);
        chosenCards = new ArrayDeque();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        selectedCard = new boolean[5];
        latestSelectedCardPos = 0;

        initializeTextures();
        makeCardButtons();
        makeConfirmationButtons();
        makeDeck();
    }

    public void createVisualCardSequencing() {
        visualCardSequencing = new GlyphLayout[5];
        font = new BitmapFont[5];
        for (int i = 0; i < 5; i++) {
            font[i] = new BitmapFont(Gdx.files.internal("res\\font\\font.fnt"), Gdx.files.internal("res\\font\\font.png"), false);
            font[i].getData().setScale(0.5f, 0.5f);
            font[i].setColor(90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
            String text = "" + (i+1);
            visualCardSequencing[i] = new GlyphLayout(font[i], text);

        }
    }

    public void initializeTextures() {
        createVisualCardSequencing();
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
        handleNextStage();
    }

    // Handles criteria necessary for proceeding to the next stage
    public void handleNextStage() {
        if (chosenCards.size() == 5 && confirmed) {
            for (int i = 0; i < 5; i++) {
                System.out.println(chosenCards.pop().priority());
                gsm.set(new PhaseState(gsm));
            }
        }
    }

    ///Draws numbers 1-5 to the position of chosen card
    //TODO adjusting for duplicates
    public void handleVisualSelection() {
        if (selectedCard.length != 0)
            drawSelectedNumber(chosenCards.size());
    }

    private void drawSelectedNumber(int selectedNum) {
        float width = ((RobotDemo.CARD_WIDTH * latestSelectedCardPos+ RobotDemo.CARD_WIDTH / 4) + visualCardSequencing[selectedNum].width );
        float height = (RobotDemo.CARD_HEIGHT - 60 + visualCardSequencing[selectedNum].height);
        font[1].draw(stage.getBatch(), visualCardSequencing[selectedNum], width, height);
//        font[selectedNum+1].draw(stage.getBatch(), visualCardSequencing[selectedNum], width, height);
    }
    public void disposeFonts() {
        for (int i = 0; i < font.length; i++)
            font[i].dispose();
    }

    public void makeConfirmationButtons() {
        confirm = new CustomImageButton("res/buttons/Confirm.png", "res/buttons/Confirm.png", RobotDemo.WIDTH - 250, RobotDemo.CARD_WIDTH / 2 + 50, 100, 50);
        reset = new CustomImageButton("res/buttons/Reset.png", "res/buttons/Reset.png", RobotDemo.WIDTH - 250, 30, 100, 50);
        confirm.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (chosenCards.size() == 5)
                    confirmed = true;
                return true;
            }
        });

        reset.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chosenCards.clear();
                disposeFonts();
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
            int nth = 0;
            cards[i].getButton().addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (!chosenCards.contains(availableRoundCard[finalI]) && chosenCards.size() < 5) {
                        chosenCards.add(availableRoundCard[finalI]);
                        selectedCard[nth] = true;
                        latestSelectedCardPos = finalI;
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
        handleVisualSelection();
        stage.getBatch().end();
        stage.draw();
    }

    public void renderTiles(SpriteBatch sb) { // Eventually should be implemented in board class, based on tile positions // board.draw();?
        stage.getBatch().draw(cardBackground, 0, 0);
        for (int i = 0; i < RobotDemo.WIDTH; i += 64)
            for (int j = 200; j < RobotDemo.HEIGHT; j += 64)
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
