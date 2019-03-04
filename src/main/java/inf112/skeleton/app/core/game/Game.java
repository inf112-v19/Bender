package inf112.skeleton.app.core.game;

import inf112.skeleton.app.core.cards.IDeck;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.ProgramDeck;

public class Game {

    private IDeck<IProgramCard> programCards;

    public Game() {
        programCards = new ProgramDeck();
    }
}
