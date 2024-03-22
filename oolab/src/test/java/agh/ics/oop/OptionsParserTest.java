package agh.ics.oop;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {

    @Test
    public void parsingValidDirections() {

        String[] args = { "f" , "b", "l", "r" };

        List<MoveDirection> result = OptionsParser.parse(args);

        assertEquals(result.get(0), MoveDirection.FORWARD);
        assertEquals(result.get(1), MoveDirection.BACKWARD);
        assertEquals(result.get(2), MoveDirection.LEFT);
        assertEquals(result.get(3), MoveDirection.RIGHT);

    }

    @Test
    public void illegalStateException() {

        String[] args = { "f", "b", "x", "r"};

        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));
    }

}
