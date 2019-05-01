package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.core.board.Board;
import inf112.skeleton.app.core.board.Position;
import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.RotateEvent;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.ProgramDeck;
import inf112.skeleton.app.core.player.Player;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.libgdx.*;
import inf112.skeleton.app.libgdx.utils.CardTextureGenerator;
import inf112.skeleton.app.libgdx.utils.VisualBoardLoader;
import inf112.skeleton.app.server.RemoteServerHandler;


import java.net.URISyntaxException;
import java.util.*;

public class RoundState extends State {

    private final boolean singlePlayer;
    private ProgramDeck deck;
    private Board board;
    private IProgramCard[] availableRoundCard;
    private int numberOfCards = 5;
    private Player player;
    private ArrayList<Player> AI;

    private CardTextureGenerator cardTextureGenerator;
    private Stage stage;
    private BitmapFont font;
    private boolean confirmed;
    private Texture cardBackground;
    private ArrayDeque<IProgramCard> chosenCards;
    private GlyphLayout[] visualCardSequencing;
    private ArrayList<Integer> selectedCardPosX;
    private VisualBoardLoader visualBoardLoader;
    private CustomImageButton confirm;
    private CustomImageButton reset;
    private Texture boardBackground;
    private boolean serverResponse;
    private RemoteServerHandler remoteServerHandler;
    private HashMap<Player, ArrayDeque<IProgramCard>> collectiveCards;
    private RemoteServerHandler.mainHandler mainHandler;

    public static final int CARD_WIDTH = 110;
    public static final int CARD_HEIGHT = 220;

    /* Upon pressing the confirm button, the cards are sent to the server. The client waits until
    every other client connected to the same Game Room selects their cards or time limit runs
    out. When the client receives new position processed by the server, it may begin rendering

 */
    public RoundState(GameStateManager gsm, Board board, Player player, boolean singlePLayer) throws URISyntaxException {
        super(gsm);
        this.board = board;
        this.player = player;
        this.singlePlayer = singlePLayer;
        numberOfCards = player.freeSlots();

        initializeObjects();
        makeDeck();

        if (!singlePLayer) {
            updateServerBoard();
        } else {
            AI = new ArrayList<>();

            Player p1 = new Player("Bender");
            Player p2 = new Player("Roberto");
            Player p3 = new Player("Beelzebot");

            AI.add(p1);
            AI.add(p2);
            AI.add(p3);

            board.addRobot(p1.getRobot(), new Position(4, 4));
            board.addRobot(p2.getRobot(), new Position(9, 9));
            board.addRobot(p3.getRobot(), new Position(0, 9));

        }

        cardTextureGenerator = new CardTextureGenerator();
        Gdx.input.setInputProcessor(stage);
        visualBoardLoader = new VisualBoardLoader(board);
        initializeTextures();
        makeCardButtons();
        makeConfirmationButtons();

    }

    private void initializeObjects() throws URISyntaxException {
        serverResponse = false;
        collectiveCards = new HashMap<>();
        chosenCards = new ArrayDeque();
        selectedCardPosX = new ArrayList();
        stage = new Stage();
        mainHandler = new RemoteServerHandler.mainHandler();
        remoteServerHandler = new RemoteServerHandler(mainHandler);
    }

    private void makeAITurns() {
        for (Player player : AI) {
            // robot might be dead
            if (!board.containsRobot(player.getRobot())) continue;
            giveCardsToAI(player);
        }
    }


    private void giveCardsToAI(Player player) {
        for (int i = 0; i < player.freeSlots(); i++) {
            board.getRobot(player.getRobot()).addCard(deck.draw()); // may need to be changed
        }
    }

    private void updateServerBoard() {
        mainHandler.handleBoardUpdate(this.board);
    }

    /**
     * Creates unique glyph layouts for each card
     */
    public void createVisualCardSequencing() {
        visualCardSequencing = new GlyphLayout[5];
        for (int i = 0; i < 5; i++) {
            // TODO : move Gdx.files.internal to SpriteLoader
            font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false);
            font.getData().setScale(0.5f, 0.5f);
            font.setColor(90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
            String text = "" + (i + 1);
            visualCardSequencing[i] = new GlyphLayout(font, text);
        }
    }

    public void initializeTextures() {
        createVisualCardSequencing();
        // TODO : move Gdx.files.internal to SpriteLoader
        cardBackground = new Texture(Gdx.files.internal("cards/Card_background1.png"));
        boardBackground = new Texture(Gdx.files.internal("boards/board_background_round.png"));

    }

    public void makeDeck() {
        deck = new ProgramDeck();
        this.drawForRound();
    }

    /**
     * Draw as in draw deck
     */
    public void drawForRound() {
        availableRoundCard = new IProgramCard[9];
        for (int i = 0; i < 9; i++) {
            availableRoundCard[i] = deck.draw();
        }
    }

    @Override
    public void handleInput() {
        if (!singlePlayer)
            handleMultiplayer();
        else
            handleSingleplayer();
    }

    private void handleSingleplayer() {
        if (confirmed) {
            makeAITurns();
            handleNextStageSingleplayer();
        }
    }

    private void handleMultiplayer() {
        if (confirmed)
            receiveServerResponse();
        if (mainHandler.getRecieved()) {
            handleNextStageMultiplayer();
            mainHandler.received(false);
        }
    }

    public void handleNextStageMultiplayer() {
        ArrayList<Player> players = mainHandler.getPlayers();
        System.out.println("players in roundstate: " + players);
        for (Player p : players) {
            if (!board.containsRobot(p.getRobot())) {
                board.addRobot(p.getRobot());
            }
            IRobot robot = board.getRobot(p.getRobot());
            ArrayList<IProgramCard> cardList = p.getCards();
            for (IProgramCard card : cardList) {
                robot.addCard(card);
            }
        }
//        mainHandler.clearPlayerList();
//        System.out.println("cards int roundstate: " + mainHandler.getPlayerCardMap());
//        for (Player player : mainHandler.getPlayerCardMap().keySet()) {
//            if (!board.containsRobot(player.getRobot())) {
//                board.addRobot(player.getRobot());
//            }
//            IRobot robot = board.getRobot(player.getRobot());
//            for (IProgramCard card : mainHandler.getPlayerCardMap().get(player)) {
//                System.out.println("adding: "+ card);
//                robot.addCard(card);
//            }
//        }

        Board boardCopy = board.copy();
        Queue<List<Event>> events = board.round();
        gsm.push(new PhaseState(gsm, boardCopy, events));
    }

    //sends a request to server every n seconds asking whether players have selected their cards
    private void receiveServerResponse() {
        mainHandler.handleServerResponse();
    }

    private void giveAllPLayersCards() {

    }

    private void sendToServer() {
        mainHandler.handleCards(chosenCards);
    }


    // Handles criteria necessary for proceeding to the next stage
    public void handleNextStageSingleplayer() {
        if (chosenCards.size() == numberOfCards && confirmed) {
            for (int i = 0; i < numberOfCards; i++)
                player.giveCardToRobot(chosenCards.removeLast());

            Board boardCopy = board.copy();
            Queue<List<Event>> events = board.round();
            gsm.push(new PhaseState(gsm, boardCopy, events));
        }
    }

    ///Draws numbers 1-5 to the position of chosen card
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
        confirm = new CustomImageButton("buttons/Confirm.png", "buttons/Confirm.png", RoboRally.WIDTH - 250, CARD_WIDTH / 2 + 50, 100, 50);
        reset = new CustomImageButton("buttons/Reset.png", "buttons/Reset.png", RoboRally.WIDTH - 250, 30, 100, 50);
        confirmed = false;
        confirm.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (chosenCards.size() == numberOfCards && !singlePlayer) {
                    sendToServer();
                    confirmed = true;
                } else if (singlePlayer && chosenCards.size() == numberOfCards)
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
        int height = (RoboRally.HEIGHT - cardBackground.getHeight()) / 10;
        stage.getBatch().draw(boardBackground, 0, 0);
        int temp = visualBoardLoader.getTileWidthHeight() * 10 / 2;
        visualBoardLoader.renderBoardCustomSize(sb, RoboRally.WIDTH / 2 - temp, cardBackground.getHeight(), height, height);

        visualBoardLoader.renderRobots(sb, board, null, 0, RoboRally.WIDTH / 2 - temp - 7, cardBackground.getHeight() - 7, true);
        // visualBoardLoader.renderRobot(sb, player.getRobot(), board, RoboRally.WIDTH / 2 - temp, cardBackground.getHeight(), visualBoardLoader.getRobotPos(), true); //TODO update for multiple

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
    }
}
