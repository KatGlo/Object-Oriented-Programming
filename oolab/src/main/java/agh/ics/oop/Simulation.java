package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.PositionAlreadyOccupiedException;
import java.util.List;
import java.util.ArrayList;

public class Simulation implements Runnable {
    private final List<Animal> animals;
    private List<MoveDirection> directions;
    private final WorldMap worldMap;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> directions, WorldMap worldMap)  {
        this.animals = new ArrayList<>();
        this.directions = directions;
        this.worldMap = worldMap;

        try {
            initializeAnimals(initialPositions);
        } catch (PositionAlreadyOccupiedException e) {
            handlePositionOccupiedException(e);
        }
    }

    private void initializeAnimals(List<Vector2d> initialPositions) throws PositionAlreadyOccupiedException {
        for (Vector2d initialPosition : initialPositions) {
            animals.add(new Animal(worldMap, initialPosition));
        }
    }

    public void run() {
        int allAnimals = animals.size();
        int allDirections = directions.size();

        if (allAnimals == 0 || allDirections == 0) {
            return;
        }

        for (int i = 0; i < allDirections; i++) {
            Animal animal = animals.get(i % allAnimals);
            MoveDirection direction = directions.get(i);
            worldMap.move(animal, direction);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public void place(Vector2d position) throws PositionAlreadyOccupiedException {
        Animal newAnimal = new Animal(worldMap, position);
        animals.add(newAnimal);
    }

    private void handlePositionOccupiedException(PositionAlreadyOccupiedException e) {
        System.err.println("Error: " + e.getMessage());
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setMoveDirections(List<MoveDirection> moveDirections) {
        this.directions = moveDirections;
    }
}
