package inf112.skeleton.app.core.cards;

public class MoveCard extends ProgramCard {

    private final int amount;
    private boolean movesBackwards;

    public MoveCard(int priority, boolean movesBackwards, int amount) {
        super(priority);
        this.amount = amount;
        this.movesBackwards = movesBackwards;
    }

    public int getAmount() {
        return amount;
    }

    public boolean movesBackwards() {
        return this.movesBackwards;
    }

    public MoveCard copy() {
        return new MoveCard(this.priority(), movesBackwards, amount);
    }
}

