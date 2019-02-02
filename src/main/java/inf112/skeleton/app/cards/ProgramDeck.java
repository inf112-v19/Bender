package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.List;

public class ProgramDeck extends Deck<IProgramCard> {

    public ProgramDeck() {
        super(standardProgramDeck());
    }

    private static Iterable<IProgramCard> standardProgramDeck() {
        List<IProgramCard> cards = new ArrayList<>();

        for (int i = 0; i < 18; i++) cards.add(new MoveCard(490 + (i * 10), MoveCard.Direction.FORWARDS, 1));
        for (int i = 0; i < 12; i++) cards.add(new MoveCard(670 + (i * 10), MoveCard.Direction.FORWARDS, 2));
        for (int i = 0; i < 6; i++) cards.add(new MoveCard(790 + (i * 10), MoveCard.Direction.FORWARDS, 3));
        for (int i = 0; i < 6; i++) cards.add(new MoveCard(430 + (i * 10), MoveCard.Direction.BACKWARDS, 1));

        for (int i = 0; i < 18; i++) cards.add(new RotateCard(80 + (i * 20), RotateCard.Direction.RIGHT));
        for (int i = 0; i < 18; i++) cards.add(new RotateCard(70 + (i * 20), RotateCard.Direction.LEFT));
        for (int i = 0; i < 6; i++) cards.add(new RotateCard(10 + (i * 10), RotateCard.Direction.UTURN));

        return cards;
    }
}
