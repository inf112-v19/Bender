package inf112.skeleton.app.core.cards;

public abstract class ProgramCard implements IProgramCard {

    private final int priority;

    public ProgramCard(int priority) {
        this.priority = priority;
    }

    @Override
    public int priority() {
        return this.priority;
    }

    public abstract void execute();
}
