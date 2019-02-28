package inf112.skeleton.app.core.card;

import inf112.skeleton.app.core.cards.*;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.HashSet;

public class CardTests {

    @Test
    public void sizeWhenRemovingCardsTest() {
        IDeck<IProgramCard> deck = new ProgramDeck();
        int size = deck.numberOfCards();
        for (int i = 0; i < size; i++) {
            IProgramCard card = deck.draw();
            assertTrue(card != null);
        }
        assertTrue(deck.empty());
        assertTrue(deck.numberOfCards() == 0);
        assertTrue(deck.draw() == null);
    }

    @Test
    public void sizeWhenInsertingCardsTest() {
        IDeck<IProgramCard> deck = new ProgramDeck();
        int size = deck.numberOfCards();
        deck.insert(new MoveCard(0, MoveCard.Direction.FORWARDS, 2));
        assertTrue(deck.numberOfCards() == size + 1);
    }


    @Test
    public void uniquePrioritiesInProgramDeckTest() {
        IDeck<IProgramCard> deck = new ProgramDeck();
        HashSet<Integer> set = new HashSet<>();
        while (deck.empty()) {
            int size = set.size();
            IProgramCard card = deck.draw();
            set.add(card.priority());
            assertTrue(size + 1 == set.size());
        }
    }
}
