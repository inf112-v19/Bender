package inf112.skeleton.app.core.enums;

import inf112.skeleton.app.core.board.Position;

public enum Direction {

    NORTH, SOUTH, EAST, WEST;

    public static final Direction[] directions = {NORTH, SOUTH, EAST, WEST};

    public Position getNewPosition(Position position) {
        switch (this) {
            case EAST: return position.east();
            case WEST: return position.west();
            case NORTH: return position.north();
            case SOUTH: return position.south();
            default: return null;
        }
    }

    public Direction getRight() {
        switch (this) {
            case SOUTH: return WEST;
            case NORTH: return EAST;
            case WEST: return NORTH;
            case EAST: return SOUTH;
            default: return null;
        }
    }

    public Direction getLeft() {
        switch (this) {
            case SOUTH: return EAST;
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
            default: return null;
        }
    }

    public Direction getOpposite() {
        switch (this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case WEST: return EAST;
            case EAST: return WEST;
            default: return null;
        }
    }

    public static Direction getFromString(String str) {
        switch (str) {
            case "NORTH": return NORTH;
            case "SOUTH": return SOUTH;
            case "EAST": return EAST;
            case "WEST": return WEST;
            default: throw new IllegalArgumentException("illegal direction string: " + str);
        }
    }

    public Direction getNewDirection(DirectionChange dirChange) {
        switch (dirChange) {
            case LEFT: return this.getLeft();
            case RIGHT: return this.getRight();
            case UTURN: return this.getOpposite();
            default: throw new IllegalArgumentException("invalid direction change: " + dirChange);
        }
    }
}
