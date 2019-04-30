package inf112.skeleton.app.core.enums;

public enum DirectionChange {
    RIGHT, LEFT, UTURN;

    public static DirectionChange getFromString(String str) {
        switch (str) {
            case "LEFT": return LEFT;
            case "RIGHT": return RIGHT;
            case "UTURN": return UTURN;
            default: throw new IllegalArgumentException("illegal directionchange: " + str);
        }
    }

    public float getAmmount() {
        switch (this) {
            case UTURN: return 180;
            case RIGHT: return -90;
            case LEFT: return 90;
            default: throw new IllegalStateException("Illegal directionchange");
        }
    }
}
