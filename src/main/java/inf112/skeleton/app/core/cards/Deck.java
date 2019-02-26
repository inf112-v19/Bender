package inf112.skeleton.app.core.cards;

import inf112.skeleton.app.interfaces.IPlayer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Deck<T> implements IDeck<T> {

    private LinkedList<T> cards;

    public Deck(Iterable<T> cards) {
        this.cards = new LinkedList<>();
        for (T card : cards) this.cards.addFirst(card);
        this.shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    @Override
    public int numberOfCards() {
        return this.cards.size();
    }

    @Override
    public T draw() {
        if (this.cards.isEmpty()) return null;
        return this.cards.pollFirst();
    }

    @Override
    public boolean empty() {
        return this.cards.isEmpty();
    }

    @Override
    public void insert(T card) {
        cards.addLast(card);
    }
}
