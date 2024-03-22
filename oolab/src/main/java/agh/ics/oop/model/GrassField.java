package agh.ics.oop.model;
import agh.ics.oop.model.util.RandomPositionGenerator;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;


public class GrassField extends AbstractWorldMap implements WorldMap{
    private final Map<Vector2d, Grass> grassMap = new HashMap<>();


    public GrassField(int grassCount) {
        super();
        placeGrasses(grassCount);
    }

    private void placeGrasses(int grassCount) {
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(
                (int) (Math.sqrt(grassCount) * 10),
                (int) (Math.sqrt(grassCount) * 10),
                grassCount
        );
        for (Vector2d grassPosition : randomPositionGenerator) {
            placeGrass(grassPosition);
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        Grass grass = grassMap.get(position);
        if (grass != null) {
            return grass;
        }

        Animal animal = animals.get(position);
        if (animal != null) {
            return animal;
        }

        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grassMap.containsKey(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Vector2d position : grassMap.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        for (Vector2d position : animals.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        Vector2d lowerLeft = new Vector2d(minX, minY);
        Vector2d upperRight = new Vector2d(maxX, maxY);

        return new Boundary(lowerLeft, upperRight);
    }

    void placeGrass(Vector2d position) {
        if (canMoveTo(position) && !isOccupied(position)) {
            Grass grass = new Grass(position);
            grassMap.put(position, grass);
            mapChanged("Grass placed at " + position);
        }
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public Vector2d upperRight() {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Vector2d position : grassMap.keySet()) {
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        for (Vector2d position : animals.keySet()) {
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        return new Vector2d(maxX, maxY);
    }

    @Override
    public Vector2d lowerLeft() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Vector2d position : grassMap.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
        }

        for (Vector2d position : animals.keySet()) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
        }

        return new Vector2d(minX, minY);
    }


}





