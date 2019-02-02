package inf112.skeleton.app.cards;

import inf112.skeleton.app.interfaces.IPlayer;

public interface IDeck<T> {

    void shuffle();

    int numberOfCards();

    /**
     * @return
     *      a card from the top of the deck
     *      null if the deck is empty
     */
    T draw();

    boolean empty();

    /**
     * Deal cards to players
     * @param players
     */
    void deal(Iterable<IPlayer> players);
}
