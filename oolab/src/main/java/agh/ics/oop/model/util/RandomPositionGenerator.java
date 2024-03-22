package agh.ics.oop.model.util;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int grassCount;
    private final List<Vector2d> allPossiblePositions;
    private final List<Vector2d> generatedPositions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.grassCount = grassCount;
        this.allPossiblePositions = generateAllPossiblePositions();
        this.generatedPositions = new ArrayList<>();
        generatedPositions();
    }

    private List<Vector2d> generateAllPossiblePositions() {
        List<Vector2d> positions = new ArrayList<>();
        for (int x = 0; x < maxWidth; x++) {
            for ( int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2d(x, y));
            }
        }
        return positions;
    }

    private void generatedPositions() {
        Collections.shuffle(allPossiblePositions);
        int count = Math.min(grassCount, allPossiblePositions.size());
        for (int i = 0; i < count; i++) {
            generatedPositions.add(allPossiblePositions.get(i));
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return generatedPositions.iterator();
    }

}