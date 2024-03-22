package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    @Test
    public void validPositionCanBeMovedTo() {
        GrassField grassField = new GrassField(5);
        assertTrue(grassField.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void occupiedPositionCannotBeMovedTo() {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(grassField, new Vector2d(2, 3));
        grassField.place(animal);
        assertFalse(grassField.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void objectAtReturnsAnimalAtPosition() {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(grassField, new Vector2d(2, 3));
        grassField.place(animal);
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void objectAtReturnsGrassAtPosition() {
        GrassField grassField = new GrassField(5);

        grassField.placeGrass(new Vector2d(2, 3));

        assertNotNull(grassField.objectAt(new Vector2d(2, 3)));
        assertTrue(grassField.objectAt(new Vector2d(2, 3)) instanceof Grass);
    }
}
