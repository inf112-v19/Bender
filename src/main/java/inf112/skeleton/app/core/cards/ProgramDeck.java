package inf112.skeleton.app.core.cards;

import java.util.ArrayList;
import java.util.List;

public class ProgramDeck extends Deck<IProgramCard> {

    public ProgramDeck() {
        super(standardProgramDeck());
    }

    private static Iterable<IProgramCard> standardProgramDeck() {
        int priorityValue = 0;
        List<IProgramCard> cards = new ArrayList<>();

        for (int i = 0; i < 18; i++) cards.add(new MoveCard(490 + (i * 10), MoveCard.MoveDirection.FORWARDS, 1));
        for (int i = 0; i < 12; i++) cards.add(new MoveCard(670 + (i * 10), MoveCard.MoveDirection.FORWARDS, 2));
        for (int i = 0; i < 6; i++) cards.add(new MoveCard(790 + (i * 10), MoveCard.MoveDirection.FORWARDS, 3));
        for (int i = 0; i < 6; i++) cards.add(new MoveCard(430 + (i * 10), MoveCard.MoveDirection.BACKWARDS, 1));

        for (int i = 0; i < 18; i++) cards.add(new RotateCard(80 + (i * 20), RotateCard.DirectionChange.RIGHT));
        for (int i = 0; i < 18; i++) cards.add(new RotateCard(70 + (i * 20), RotateCard.DirectionChange.LEFT));
        for (int i = 0; i < 6; i++) cards.add(new RotateCard(10 + (i * 10), RotateCard.DirectionChange.UTURN));

        return cards;
    }
}
