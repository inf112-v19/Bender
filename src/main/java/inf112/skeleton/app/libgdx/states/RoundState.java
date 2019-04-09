package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.ProgramDeck;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.robot.Robot;
import inf112.skeleton.app.libgdx.*;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RoundState extends State {

    private ProgramDeck deck;
    private Board board;
    private IProgramCard[] availableRoundCard;
    private int numberOfCards = 5;
    private Player player;
    private Robot otherRobot;

    private CardTextureGenerator cardTextureGenerator;
    private Stage stage;
    private BitmapFont font;
    private boolean confirmed;
    private TextureEditor textureEditor;
    private Texture tileTexture;
    private Texture cardBackground;
    private ArrayDeque<IProgramCard> chosenCards;
    private GlyphLayout[] visualCardSequencing;
    private ArrayList<Integer> selectedCardPosX;
    private VisualBoardLoader visualBoardLoader;
    private CustomImageButton confirm;
    private CustomImageButton reset;
    private Texture boardBackground;
    public static final int CARD_WIDTH = 110;
    public static final int CARD_HEIGHT = 220;

    public RoundState(GameStateManager gsm) {
        super(gsm);

        player = new Player("username");
        otherRobot = new Robot(Direction.NORTH);
        board = new Board("empty", 10, 10);
        board.addRobot(otherRobot, new Position(6, 6));
        board.addRobot(player.getRobot(), new Position(5, 5));
        chosenCards = new ArrayDeque();

        textureEditor = new TextureEditor();
        cardTextureGenerator = new CardTextureGenerator();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        selectedCardPosX = new ArrayList();
        visualBoardLoader = new VisualBoardLoader(board);
        initializeTextures();
        makeDeck();
        makeCardButtons();
        makeConfirmationButtons();
    }

    //Creates unique glyph layouts for each card
    public void createVisualCardSequencing() {
        visualCardSequencing = new GlyphLayout[5];
        for (int i = 0; i < 5; i++) {
            font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false);
            font.getData().setScale(0.5f, 0.5f);
            font.setColor(90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
            String text = "" + (i + 1);
            visualCardSequencing[i] = new GlyphLayout(font, text);

        }
    }

    public void initializeTextures() {
        createVisualCardSequencing();
        cardBackground = new Texture(Gdx.files.internal("cards/Card_background1.png"));
        tileTexture = new Texture(Gdx.files.internal("tiles/empty_tile.png"));
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_round.png"));

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
        if (chosenCards.size() == numberOfCards && confirmed) {
            Queue<List<Move>> moves = new ArrayDeque<>();
            List<Move> moveList = new ArrayList<>();
            Position start = new Position(5, 5);
            Position end = new Position(5, 4);
            Move move = new Move(player.getRobot(), start, end);
            Move move2 = new Move(otherRobot, new Position(6, 6), new Position(5, 6));
            moveList.add(move);
            moveList.add(move2);
            moves.add(moveList);
            for (int i = 0; i < numberOfCards; i++)
                player.giveCardToRobot(chosenCards.removeLast());
            gsm.push(new PhaseState(gsm, board, moves));
        }
    }

    ///Draws numbers 1-5 to the position of chosen card
    //TODO adjusting for duplicates
    //visualCardSequencing goes from 0 to 4 (included), rather than 1 to 5, hence the chosenCards.size() -1
    public void handleVisualSelection() {
        if (chosenCards.size() > 0 && chosenCards.size() <= numberOfCards)
            for (int i = 0; i < chosenCards.size(); i++) {
                drawSelectedNumber(chosenCards.size() - 1);
            }
    }

    //Draw as in paint
    //Draws the currently selected card's number on board
    private void drawSelectedNumber(int selectedNum) {
        BitmapFontCache bc = new BitmapFontCache(font);
        float yPos = (CARD_HEIGHT - 65 + visualCardSequencing[selectedNum].height);
        for (int i = 0; i < selectedCardPosX.size(); i++) {
            float xPos = ((CARD_WIDTH * selectedCardPosX.get(i) + CARD_WIDTH / 4) + visualCardSequencing[i].width / 2 + CARD_WIDTH / 2);
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
        confirm = new CustomImageButton("buttons/Confirm.png", "buttons/Confirm.png", RobotDemo.WIDTH - 250, CARD_WIDTH / 2 + 50, 100, 50);
        reset = new CustomImageButton("buttons/Reset.png", "buttons/Reset.png", RobotDemo.WIDTH - 250, 30, 100, 50);
        confirmed = false;
        confirm.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (chosenCards.size() == numberOfCards)
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
        CustomImageButton[] cards = new CustomImageButton[9];

        for (int i = 0; i < 9; i++) {
            int x = CARD_WIDTH * i + CARD_WIDTH / 4;
            int y = 18;
            int width = CARD_WIDTH - 30;
            int height = CARD_HEIGHT - 60;
            Texture cardTexture = cardTextureGenerator.generateTexture(availableRoundCard[i]);
            cards[i] = new CustomImageButton(cardTexture, "cards/card.png", x, y, width, height);

            final int finalI = i;
            cards[i].getButton().addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (!chosenCards.contains(availableRoundCard[finalI]) && chosenCards.size() <= numberOfCards) {
                        chosenCards.add(availableRoundCard[finalI]);
                        selectedCardPosX.add(finalI);
                    }

                    return true;
                }
            });
            stage.addActor(cards[i].getButton());
        }
    }

    private void drawCardMoveAmount() { //eventually should be moved to CardTextureGenerator
        for (int i = 0; i < 9; i++) {
            if (availableRoundCard[i] instanceof MoveCard) {
                float yPos = 73;
                float xPos = ((CARD_WIDTH * (i + 1) + CARD_WIDTH / 4) + 171 / 2 - CARD_WIDTH / 2) - 110;
                MoveCard card = (MoveCard) availableRoundCard[i];
                cardTextureGenerator.drawCardMoveAmount(card, xPos, yPos, stage);
            }
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
        this.drawCardMoveAmount();
    }

    public void renderBoard(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        int height = (RobotDemo.HEIGHT - cardBackground.getHeight()) / 10;
        stage.getBatch().draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoardCustomSize(sb, RobotDemo.WIDTH / 2 - temp, cardBackground.getHeight(), height, height);
        visualBoardLoader.renderRobot(sb, player.getRobot(), RobotDemo.WIDTH / 2 - temp, cardBackground.getHeight(), visualBoardLoader.getRobotPos(), true);
        stage.getBatch().draw(cardBackground, 0, 0);

    }

    public void reInitialize() {
        chosenCards = new ArrayDeque();
        selectedCardPosX = new ArrayList();
        initializeTextures();
        makeDeck();
        makeCardButtons();
        makeConfirmationButtons();
    }

    @Override
    public void dispose() {
        confirm.getTexture().dispose();
        reset.getTexture().dispose();
        cardBackground.dispose();
        tileTexture.dispose();
    }
}
