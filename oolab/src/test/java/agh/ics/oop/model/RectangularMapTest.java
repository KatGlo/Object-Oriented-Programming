package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangularMapTest {

    @Test
    public void canMoveToValidPositionReturnsTrue() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        assertTrue(rectangularMap.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void canMoveToOccupiedPositionReturnsFalse() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 3));
        rectangularMap.place(animal);
        assertFalse(rectangularMap.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void placeValidPositionReturnsTrue() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 3));
        assertTrue(rectangularMap.place(animal));
    }

    @Test
    public void placeOccupiedPositionReturnsFalse() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal1 = new Animal(rectangularMap, new Vector2d(2, 3));
        Animal animal2 = new Animal(rectangularMap, new Vector2d(2, 3));
        rectangularMap.place(animal1);
        assertFalse(rectangularMap.place(animal2));
    }


}



