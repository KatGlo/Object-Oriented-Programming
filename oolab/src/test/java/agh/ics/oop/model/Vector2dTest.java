package agh.ics.oop.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void addShouldReturnCorrectResult() {
        //given
        Vector2d vector1 = new Vector2d(0, 1);
        Vector2d vector2 = new Vector2d(2, 0);

        //when
        Vector2d vector3 = vector1.add(vector2);

        //then
        assertEquals(2, vector3.getX());
        assertEquals(1, vector3.getY());
    }

    @Test
    public void subtractShouldReturnCorrectResult() {

        Vector2d vector1 = new Vector2d(0, 1);
        Vector2d vector2 = new Vector2d(2, 0);

        Vector2d vector3 = vector1.subtract(vector2);

        assertEquals(-2, vector3.getX());
        assertEquals(1, vector3.getY());
    }
    @Test
    public void oppositeShouldReturnOppositeVector() {

        Vector2d vector1 = new Vector2d(0, 0);
        Vector2d vector2 = new Vector2d(2, 1);
        Vector2d vector3 = new Vector2d(-2, -1);

        Vector2d opposite1 = vector1.opposite();
        Vector2d opposite2 = vector2.opposite();
        Vector2d opposite3 = vector3.opposite();

        assertEquals(opposite1, vector1.opposite());
        assertEquals(opposite2, vector2.opposite());
        assertEquals(opposite3, vector3.opposite());
    }

    @Test
    public void equalsShouldCompareVectors() {

        Vector2d vector1 = new Vector2d(0, 0);
        Vector2d vector2 = new Vector2d(0, 0);
        Vector2d vector3 = new Vector2d(0, 1);

        assertEquals(vector1, vector2);
        assertNotEquals(vector1, vector3);

    }

    @Test
    public void toStringShouldReturnCorrectStringRepresentation() {

        Vector2d vector = new Vector2d(0, 0);

        assertEquals(vector.toString(),"(0,0)");

    }

    @Test
    public void precedesShouldCheckIfVectorPrecedesGivenVector() {

        Vector2d vector1 = new Vector2d(0, 0);
        Vector2d vector2 = new Vector2d(1, 2);

        assertTrue(vector1.precedes(vector2));
        assertFalse(vector2.precedes(vector1));
        assertTrue(vector2.precedes(vector2));
    }

    @Test
    public void followsShouldCheckIfVectorFollowsGivenVector() {

        Vector2d vector1 = new Vector2d(0, 0);
        Vector2d vector2 = new Vector2d(1, 2);

        assertTrue(vector2.follows(vector1));
        assertFalse(vector1.follows(vector2));
        assertTrue(vector2.follows(vector2));
    }

    @Test
    public void upperRightShouldReturnUpperRightVector() {

        Vector2d vector1 = new Vector2d(0, 1);
        Vector2d vector2 = new Vector2d(2, 0);
        Vector2d vector3 = new Vector2d(2, 1);

        Vector2d upperRight = vector1.upperRight(vector2);

        assertEquals(vector3, upperRight);
    }

    @Test
    public void lowerLeftShouldReturnLowerLeftVector() {

        Vector2d vector1 = new Vector2d(0, 1);
        Vector2d vector2 = new Vector2d(2, 0);
        Vector2d vector3 = new Vector2d(0, 0);

        Vector2d lowerLeft = vector1.lowerLeft(vector2);

        assertEquals(vector3, lowerLeft);
    }

    @Test
    public void hashCodeShouldReturnHashCode() {

        Vector2d vector1 = new Vector2d(2, 1);
        Vector2d vector2 = new Vector2d(2, 1);

        assertEquals(vector1.hashCode(), vector2.hashCode());

    }


}
