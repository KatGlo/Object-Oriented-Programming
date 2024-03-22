package agh.ics.oop;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
     void runSimulationWithGrassField(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        GrassField grassField = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, grassField);
        simulation.run();

        for (int i = 0; i < positions.size(); i++) {
            Animal animal = simulation.getAnimal(i);
            assertTrue(grassField.isOccupied(animal.getPosition()));
        }
    }

    @Test
    void runSimulationWithRectangularMap(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        RectangularMap rectangularMap = new RectangularMap(10, 5);
        Simulation simulation = new Simulation(positions, directions, rectangularMap);
        simulation.run();

        for (int i = 0; i < positions.size(); i++) {
            Animal animal = simulation.getAnimal(i);
            assertTrue(rectangularMap.isOccupied(animal.getPosition()));
        }
    }
}