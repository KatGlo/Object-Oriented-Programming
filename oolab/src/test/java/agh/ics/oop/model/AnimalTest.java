package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void correctsStringRepresentation() {
        Animal animal = new Animal(new RectangularMap(5, 5), new Vector2d(2, 2));
//        assertEquals("Animal position: (2,2), orientation: Północ", animal.toString());
        assertEquals("A", animal.toString());
    }

    @Test
    void animalAtSpecificPosition() {
        Animal animal = new Animal(new RectangularMap(5, 5), new Vector2d(3, 3));
        assertTrue(animal.isAt(new Vector2d(3, 3)));
        assertFalse(animal.isAt(new Vector2d(2, 2)));
    }

    @Test
    void updatesPositionAndOrientation() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 2));
        MoveValidator validator = position -> true;
        animal.move(MoveDirection.FORWARD, validator);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void returnsCorrectPosition() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(3, 4));
        assertEquals(new Vector2d(3, 4), animal.getPosition());
    }

    @Test
    void returnsCorrectOrientation() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 2));
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void ifCorrectMoveForward() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 2));
        MoveValidator validator = position -> true;

        animal.move(MoveDirection.FORWARD, validator);

        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void ifCorrectMoveBackward() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 2));
        MoveValidator validator = position -> true;

        animal.move(MoveDirection.BACKWARD, validator);

        assertEquals(new Vector2d(2, 1), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void ifCorrectMoveRight() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 2));
        MoveValidator validator = position -> true;

        animal.move(MoveDirection.RIGHT, validator);

        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.EAST, animal.getOrientation());
    }

    @Test
    void ifCorrectMoveLeft() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal = new Animal(rectangularMap, new Vector2d(2, 2));
        MoveValidator validator = position -> true;

        animal.move(MoveDirection.LEFT, validator);

        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.WEST, animal.getOrientation());
    }
}
