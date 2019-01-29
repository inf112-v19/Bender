package inf112.skeleton.app.cards;

import inf112.skeleton.app.interfaces.IPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.Stack;

public class Deck<T extends ICard> implements IDeck<T> {

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
        throw new NotImplementedException();
    }
}
