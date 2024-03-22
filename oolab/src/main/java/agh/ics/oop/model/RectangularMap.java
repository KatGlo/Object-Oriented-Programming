package agh.ics.oop.model;
import java.util.UUID;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private final int width;
    private final int height;
    private final UUID id;

    public RectangularMap(int width, int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than zero");
        }
        this.width = width;
        this.height = height;
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean isInBounds = position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width - 1, height - 1));
        boolean isOccupied = isOccupied(position);

        System.out.println("Can move to " + position + ": " + (isInBounds && !isOccupied));
        return isInBounds && !isOccupied;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();

        if (canMoveTo(animalPosition)) {
            animals.put(animalPosition, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d currentPosition = animal.getPosition();
        Vector2d newPosition = currentPosition.add(direction.toUnitVector());

        if (canMoveTo(newPosition)) {
            animals.remove(currentPosition);
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
        }
    }


    @Override
    public Animal objectAt(Vector2d position) {

        return animals.get(position);
    }
    @Override
    public Vector2d lowerLeft() {
        return new Vector2d(0, 0);
    }
    @Override
    public Vector2d upperRight() {
        return new Vector2d(width - 1, height - 1);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    @Override
    public UUID getId() {
        return id;
    }
}

