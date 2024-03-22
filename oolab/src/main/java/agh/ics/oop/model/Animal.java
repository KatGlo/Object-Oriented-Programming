package agh.ics.oop.model;
public class Animal implements WorldElement {
    private static final MapDirection INITIAL_ORIENTATION = MapDirection.NORTH;
    private static final Vector2d INITIAL_POSITION = new Vector2d(2, 2);
    private MapDirection orientation;
    private Vector2d position;

    private final WorldMap worldMap;

    public Animal(WorldMap worldMap) {
        this(worldMap, INITIAL_POSITION);
    }

    public Animal(WorldMap worldMap, Vector2d initialPosition) {
        this.orientation = INITIAL_ORIENTATION;
        this.position = initialPosition;
        this.worldMap = worldMap;
    }

    @Override
    public String toString() {

        //return "Animal position: " + position + ", orientation: " + this.orientation;
        return "A";
    }

    public boolean isAt(Vector2d position) {

        return this.position.equals(position);
    }

    void move(MoveDirection direction, MoveValidator validator) {
        Vector2d newPosition = switch (direction) {
            case FORWARD -> this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> this.position.subtract(this.orientation.toUnitVector());
            case RIGHT -> {
                this.orientation = this.orientation.next();
                yield this.position;
            }
            case LEFT -> {
                this.orientation = this.orientation.previous();
                yield this.position;
            }
            default -> throw new IllegalArgumentException("Unsupported direction: " + direction);
        };

        if (validator != null && validator.canMoveTo(newPosition)) {
            if (worldMap.isOccupied(newPosition)) {
                System.out.println("Cannot move " + direction + " to: " + newPosition + ", orientation: " + this.orientation +
                        ", position occupied by another animal");
            } else {
                this.position = newPosition;
                System.out.println("Moved " + direction + " to: " + this.position + ", orientation: " + this.orientation);
            }
        } else {
            System.out.println("Cannot move " + direction + " to: " + newPosition + ", orientation: " + this.orientation);
        }
    }
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }
}
