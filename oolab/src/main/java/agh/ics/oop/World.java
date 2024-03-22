package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.*;
import agh.ics.oop.Simulation;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("Program rozpoczął działanie.");
        int numberOfSimulations = 1000;
        List<Simulation> simulations = new ArrayList<>();

        for (int i = 0; i <= numberOfSimulations; i++) {
            GrassField grassField = new GrassField(20);
            Simulation simulation = new Simulation(List.of(new Vector2d(0, 0)), new ArrayList<>(), grassField);
            simulations.add(simulation);
        }

        ConsoleMapDisplay sharedConsoleDisplay = new ConsoleMapDisplay();

        SimulationEngine engine = new SimulationEngine(simulations);
        engine.addSimulationObserver(sharedConsoleDisplay);
        engine.runAsyncInThreadPool();
        engine.awaitSimulationsEnd();

        WorldMap map = simulations.get(0).getWorldMap();
        System.out.println("Final state:\n" + map);

        System.out.println("Program zakończył działanie.");

    }
}


