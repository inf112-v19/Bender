package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.List;

public class ProgramDeck extends Deck<IProgramCard> {

    public ProgramDeck() {
        super(standardProgramDeck());
    }

    private static Iterable<IProgramCard> standardProgramDeck() {
        List<IProgramCard> cards = new ArrayList<>();
        
        for (int i = 0; i < 6; i++)     cards.add(new RotateCard(priority(), RotateCard.Direction.UTURN));       
        
        for (int i = 0; i < 36; i++) {  cards.add(new RotateCard(priority(), RotateCard.Direction.RIGHT));
                                        cards.add(new RotateCard(priority(), RotateCard.Direction.LEFT));  }
        
        for (int i = 0; i < 18; i++)    cards.add(new MoveCard(priority(), MoveCard.Direction.FORWARDS, 1));
        for (int i = 0; i < 12; i++)    cards.add(new MoveCard(priority(), MoveCard.Direction.FORWARDS, 2));
        for (int i = 0; i < 6; i++)     cards.add(new MoveCard(priority(), MoveCard.Direction.FORWARDS, 3));
        for (int i = 0; i < 6; i++)     cards.add(new MoveCard(priority(), MoveCard.Direction.BACKWARDS, 1));
        
        return cards;
    }

    private static int priority() {
        for (j = 10; j <= 840; j += 10) {
            return j;
        }
    }
}
