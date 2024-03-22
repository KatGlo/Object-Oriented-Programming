package agh.ics.oop.model;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();
    protected final UUID id;
    public AbstractWorldMap() {
        this.id = UUID.randomUUID();
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }



    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        if (!canMoveTo(animal.getPosition())){
            throw new IllegalArgumentException("You cannot place an animal in the position outside the map");
        }
        if (this.isOccupied(animal.getPosition())){
            throw new IllegalArgumentException("You cannot place an animal in the position of another animal");
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }

    private boolean validateAndPlace(Animal animal, Vector2d position) {
        if (isOccupied(position)) {
            return false;
        }
        animals.put(position, animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);

            if (validateAndPlace(animal, newPosition)) {
                mapChanged("Animal moved from " + oldPosition + " to " + newPosition);
            } else {
                mapChanged("Failed to move animal from " + oldPosition + " to " + newPosition);
                animal.move(MoveDirection.BACKWARD, this);
                animals.put(oldPosition, animal);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {

        return animals.containsKey(position);
    }

    @Override
    public abstract WorldElement objectAt(Vector2d position);

    @Override
    public Collection<WorldElement> getElements() {

        return new ArrayList<>(animals.values());
    }

    public abstract Boundary getCurrentBounds();

    protected String drawMap(Vector2d lowerLeft, Vector2d upperRight) {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public String toString() {
        Boundary bounds = getCurrentBounds();
        Vector2d loweLeft = bounds.lowerLeft();
        Vector2d upperRight = bounds.upperRight();
        return drawMap(loweLeft, upperRight);
    }


    protected void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    public UUID getId() {
        return id;
    }


}

