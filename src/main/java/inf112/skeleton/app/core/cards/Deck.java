package inf112.skeleton.app.core.cards;

import inf112.skeleton.app.interfaces.IPlayer;

import java.util.Collections;
import java.util.Stack;

public class Deck<T> implements IDeck<T> {

    private Stack<T> cards;

    public Deck(Iterable<T> cards) {
        cards = new Stack<>();
        for (T card : cards) ((Stack<T>) cards).push(card);
        this.shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public int numberOfCards() {
        return cards.size();
    }

    @Override
    public T draw() {
        if (cards.empty()) return null;
        return cards.pop();
    }

    @Override
    public boolean empty() {
        return cards.empty();
    }

    @Override
    public void deal(Iterable<IPlayer> players) {
        // TODO: implement
    }
}
