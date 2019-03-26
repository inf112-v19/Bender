package inf112.skeleton.app.core.cards;

import inf112.skeleton.app.core.enums.DirectionChange;

import java.util.ArrayList;
import java.util.List;

public class ProgramDeck extends Deck<IProgramCard> {

    public ProgramDeck() {
        super(standardProgramDeck());
    }

    public ProgramDeck(Iterable<IProgramCard> cards) {
        super(cards);
    }

    private static Iterable<IProgramCard> standardProgramDeck() {
        int priorityValue = 0;
        List<IProgramCard> cards = new ArrayList<>();

        for (int i = 0; i < 18; i++) cards.add(new MoveCard(490 + (i * 10), false, 1));
        for (int i = 0; i < 12; i++) cards.add(new MoveCard(670 + (i * 10), false, 2));
        for (int i = 0; i < 6; i++) cards.add(new MoveCard(790 + (i * 10), false, 3));
        for (int i = 0; i < 6; i++) cards.add(new MoveCard(430 + (i * 10), true, 1));

        for (int i = 0; i < 18; i++) cards.add(new RotateCard(80 + (i * 20), DirectionChange.RIGHT));
        for (int i = 0; i < 18; i++) cards.add(new RotateCard(70 + (i * 20), DirectionChange.LEFT));
        for (int i = 0; i < 6; i++) cards.add(new RotateCard(10 + (i * 10), DirectionChange.UTURN));

        return cards;
    }
}
