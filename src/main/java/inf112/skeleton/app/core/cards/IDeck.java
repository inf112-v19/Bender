package inf112.skeleton.app.core.cards;

public interface IDeck<T> {

    void shuffle();

    int numberOfCards();

    /**
     * @return a card from the top of the deck
     * null if the deck is empty
     */
    T draw();

    boolean empty();

    /**
     * Insert a card at the bottom of the deck
     *
     * @param card
     */
    void insert(T card);
}