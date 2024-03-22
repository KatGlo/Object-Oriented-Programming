package agh.ics.oop.model;
import agh.ics.oop.model.WorldMap;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updateCount = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println("Update " + (++updateCount) + " (Map ID: " + worldMap.getId() + "): " + message);
        System.out.println(worldMap);
    }

}
