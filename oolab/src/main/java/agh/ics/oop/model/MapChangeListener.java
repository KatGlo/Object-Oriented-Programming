package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.model.WorldMap;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);
}
