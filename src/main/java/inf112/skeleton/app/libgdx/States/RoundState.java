package inf112.skeleton.app.libgdx.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.ProgramDeck;
import inf112.skeleton.app.libgdx.RobotDemo;
import inf112.skeleton.app.libgdx.VisualBoardLoader;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

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
    private BitmapFont font;
    private GlyphLayout[] visualCardSequencing;
    private ArrayList<Integer> selectedCardPosX;
    private VisualBoardLoader visualBoardLoader;


    private CustomImageButton confirm;
    private CustomImageButton reset;
    private Texture boardBackground;

    //TODO code quality, remove unnecessary stuff
    public RoundState(GameStateManager gsm) throws IOException {
        super(gsm);
        chosenCards = new ArrayDeque();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        selectedCard = new boolean[5];
        selectedCardPosX = new ArrayList();
        visualBoardLoader = new VisualBoardLoader("res/boards/sampleboard1.txt");

        initializeTextures();
        makeCardButtons();
        makeConfirmationButtons();
        makeDeck();
    }

    //Creates unique glyph layouts for each card
    public void createVisualCardSequencing() {
        visualCardSequencing = new GlyphLayout[5];
        for (int i = 0; i < 5; i++) {
            font = new BitmapFont(Gdx.files.internal("res/font/font.fnt"), Gdx.files.internal("res/font/font.png"), false);
            font.getData().setScale(0.5f, 0.5f);
            font.setColor(90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
            String text = "" + (i + 1);
            visualCardSequencing[i] = new GlyphLayout(font, text);

        }
    }

    public void initializeTextures() {
        createVisualCardSequencing();
        cardBackground = new Texture(Gdx.files.internal("res/Card_background1.png"));
        tileTexture = new Texture(Gdx.files.internal("res/tiles/empty_tile.png"));
        boardBackground = new Texture(Gdx.files.internal("res/boards/board_background_round.png"));

    }

    public void makeDeck() {
        deck = new ProgramDeck();
        this.drawForRound();
    }

    //Draw as in draw deck
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
            for (int i = 0; i < 5; i++)
                System.out.println(chosenCards.pop().priority());
            try {
                gsm.set(new PhaseState(gsm));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ///Draws numbers 1-5 to the position of chosen card
    //TODO adjusting for duplicates
    //visualCardSequencing goes from 0 to 4 (included), rather than 1 to 5, hence the chosenCards.size() -1
    public void handleVisualSelection() {
        if (chosenCards.size() > 0 && chosenCards.size() < 6)
            for (int i = 0; i <chosenCards.size() ; i++) {
                drawSelectedNumber(chosenCards.size() - 1);
            }
    }

    //Draw as in paint
    //Draws the currently selected card's number on board
    private void drawSelectedNumber(int selectedNum) {
        BitmapFontCache bc = new BitmapFontCache(font);
        float yPos = (RobotDemo.CARD_HEIGHT - 60 + visualCardSequencing[selectedNum].height);
        for (int i = 0; i <selectedCardPosX.size() ; i++) {
            float xPos = ((RobotDemo.CARD_WIDTH * selectedCardPosX.get(i) + RobotDemo.CARD_WIDTH / 4) + visualCardSequencing[i].width / 2);
            bc.addText(visualCardSequencing[i], xPos, yPos);
        }
        bc.draw(stage.getBatch());
    }

    public void disposeFonts() {
        font.dispose();
        selectedCardPosX.clear();
        createVisualCardSequencing();
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
                createVisualCardSequencing();
                return true;
            }
        });

        stage.addActor(confirm.getButton());
        stage.addActor(reset.getButton());
    }


    //Each card button has a listener for user input
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
                        selectedCardPosX.add(finalI);
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
        this.renderBoard((SpriteBatch) stage.getBatch());
        handleVisualSelection();
        stage.getBatch().end();
        stage.draw();
    }

    public void renderBoard(SpriteBatch sb) {
        int height = (RobotDemo.HEIGHT - cardBackground.getHeight()) / 10;
        stage.getBatch().draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoardCustomSize(sb, RobotDemo.WIDTH / 2 - temp, cardBackground.getHeight(), height, height);
        stage.getBatch().draw(cardBackground, 0, 0);

    }

    @Override
    public void dispose() {
        confirm.getTexture().dispose();
        reset.getTexture().dispose();
        cardBackground.dispose();
        tileTexture.dispose();

    }
}
