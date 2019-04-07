package inf112.skeleton.app.core.enums;

public enum DirectionChange {
    RIGHT, LEFT, UTURN;

    public static DirectionChange getFromString(String str) {
        switch (str) {
            case "LEFT": return LEFT;
            case "RIGHT": return RIGHT;
            case "UTURN": return UTURN;
            default: return null;
        }
    }
}
