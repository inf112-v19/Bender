package inf112.skeleton.app.core.cards;

public class MoveCard extends ProgramCard {


    private final int ammount;
    private boolean movesBackwards;

    public MoveCard(int priority, boolean movesBackwards, int ammount) {
        super(priority);
        this.ammount = ammount;
        this.movesBackwards = movesBackwards;
    }

    public int getAmmount() {
        return ammount;
    }

    public boolean movesBackwards() {
        return this.movesBackwards;
    }
}

