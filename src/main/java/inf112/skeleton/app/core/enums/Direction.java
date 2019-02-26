package inf112.skeleton.app.core.enums;

import inf112.skeleton.app.core.position.Position;

public enum Direction {

    NORTH, SOUTH, EAST, WEST;

    public Position getNewPosition(Position position) {
        switch (this) {
            case EAST: return position.east();
            case WEST: return position.west();
            case NORTH: return position.north();
            case SOUTH: return position.south();
            default: return null;
        }
    }
}
