package agh.ics.oop.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    @Test
    public void nextShouldReturnCorrectDirections (){

        Assertions.assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
    }

    @Test
    public void previousShouldReturnCorrectDirections() {

        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
    }

    @Test
    public void toStringShouldReturnCorrectStrings() {

        assertEquals("Północ", MapDirection.NORTH.toString());
        assertEquals("Południe", MapDirection.SOUTH.toString());
        assertEquals("Zachód", MapDirection.WEST.toString());
        assertEquals("Wschód", MapDirection.EAST.toString());
    }

    @Test
    public void toUnitVectorShouldReturnCorrectUnitVectors() {

        Assertions.assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector());
        assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector());
    }

}
