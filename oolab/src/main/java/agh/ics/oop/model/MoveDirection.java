package agh.ics.oop.model;
public enum MoveDirection {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT;

    public Vector2d toUnitVector() {
        MapDirection mapDirection = mapDirectionFromMoveDirection(this);
        return mapDirection.toUnitVector();
    }

    private MapDirection mapDirectionFromMoveDirection(MoveDirection moveDirection) {
        switch (moveDirection) {
            case FORWARD:
                return MapDirection.NORTH;
            case BACKWARD:
                return MapDirection.SOUTH;
            case LEFT:
                return MapDirection.WEST;
            case RIGHT:
                return MapDirection.EAST;
            default:
                throw new IllegalArgumentException("Unsupported MoveDirection: " + moveDirection);
        }
    }
}
