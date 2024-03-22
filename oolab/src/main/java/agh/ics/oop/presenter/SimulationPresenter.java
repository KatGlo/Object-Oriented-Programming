package agh.ics.oop.presenter;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.AbstractWorldMap;
import agh.ics.oop.model.MapChangeListener;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener{
    @FXML
    private BorderPane root;
    @FXML
    private Canvas mapCanvas;
    @FXML
    private GridPane mapGrid;
    @FXML
    private TextField movementTextField;
    @FXML
    private Label infoLabel;
    @FXML
    private Label descriptionLabel;
    private WorldMap worldMap;
    private Simulation simulation;
    private List<MoveDirection> moveDirections;
    private int curretnMoveIndex;

    private static int simulationCount = 0;
    private final int CELL_WIDTH = 20;
    private final int CELL_HEIGHT = 20;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
        if (worldMap instanceof AbstractWorldMap) {
            ((AbstractWorldMap) worldMap).addObserver(this);
        }
        curretnMoveIndex = 0;
        drawMap(mapCanvas.getGraphicsContext2D());
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }



    private void drawMap(GraphicsContext gc) {
        clearGrid();

        Vector2d lowerLeft = worldMap.lowerLeft();
        Vector2d upperRight = worldMap.upperRight();

        int tileSize = 20;
        int width = upperRight.getX() - lowerLeft.getX() + 1;
        int height = upperRight.getY() - lowerLeft.getY() + 1;

        double scaleX = mapCanvas.getWidth() / width;
        double scaleY = mapCanvas.getHeight() / height;
        double scale = Math.min(scaleX, scaleY);

        for (int y = lowerLeft.getY(); y <= upperRight.getY(); y++) {
            for (int x = lowerLeft.getX(); x <= upperRight.getX(); x++) {
                Vector2d currentPosition = new Vector2d(x, y);
                WorldElement elementAtPosition = worldMap.objectAt(currentPosition);

                if (elementAtPosition instanceof Animal) {
                    int rectX = (x - lowerLeft.getX()) * tileSize;
                    int rectY = (y - lowerLeft.getY()) * tileSize;

                    gc.setFill(Color.GREEN);
                    gc.fillOval(rectX, rectY, tileSize, tileSize);
                } else if (elementAtPosition instanceof Grass) {
                    int rectX = (x - lowerLeft.getX()) * tileSize;
                    int rectY = (y - lowerLeft.getY()) * tileSize;

                    gc.setFill(Color.LIGHTGREEN);
                    gc.fillRect(rectX, rectY, tileSize, tileSize);
                }
            }
        }


        for (int i = 0; i < width; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }

        for (int i = 0; i < height; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().clear();
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }


    @Override
    public void mapChanged(WorldMap map, String message) {
        Platform.runLater(() -> {
            infoLabel.setText(message);
            drawMap(mapCanvas.getGraphicsContext2D());
        });
    }

//    @FXML
//    private void onSimulationStartClicked() {
//        String[] inputMoves = movementTextField.getText().split(",");
//        List<MoveDirection> moveDirections = new ArrayList<>();
//
//        for (String inputMove : inputMoves) {
//            MoveDirection direction = MoveDirection.valueOf(inputMove.toUpperCase());
//            if (direction != null) {
//                moveDirections.add(direction);
//            }
//        }
//
//        if (moveDirections.isEmpty() || simulation == null) {
//            return;
//        }
//
//        Thread simulationThread = new Thread(() -> {
//            simulation.setMoveDirections(moveDirections);
//            simulation.run();
//
//            try {
//                Thread.sleep(500 * moveDirections.size());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Platform.runLater(() -> {
//                drawMap(mapCanvas.getGraphicsContext2D());
//            });
//        });
//
//        simulationThread.start();
//    }


//ZadanieDodatkowe
    @FXML
    private void onSimulationStartClicked() {
        String[] inputMoves = movementTextField.getText().split(",");
        List<MoveDirection> moveDirections = new ArrayList<>();

        for (String inputMove : inputMoves) {
            MoveDirection direction = MoveDirection.valueOf(inputMove.toUpperCase());
            if (direction != null) {
                moveDirections.add(direction);
            }
        }

        if (moveDirections.isEmpty() || simulation == null) {
            return;
        }

        Stage newStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("simulation.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        newStage.setScene(new Scene(root));

        newStage.setTitle("Symulacja " + (++simulationCount));

        newStage.show();

        Simulation newSimulation = new Simulation(
                List.of(new Vector2d(2, 2), new Vector2d(3, 3)),
                new ArrayList<>(),
                worldMap
        );

        Thread simulationThread = new Thread(() -> {
            newSimulation.setMoveDirections(moveDirections);
            newSimulation.run();

            try {
                Thread.sleep(500 * moveDirections.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                drawMap(mapCanvas.getGraphicsContext2D());
            });
        });

        simulationThread.start();
    }




    @FXML
    private void initialize() {
        int initialGrassCount = 20;
        worldMap = new GrassField(initialGrassCount);
        ((AbstractWorldMap) worldMap).addObserver(this);

        GraphicsContext gc = mapCanvas.getGraphicsContext2D();

        simulation = new Simulation(
                List.of(new Vector2d(2, 2), new Vector2d(3, 3)),
                new ArrayList<>(),
                worldMap
        );
        drawMap(gc);
        GridPane.setHalignment(infoLabel, HPos.CENTER);
        GridPane.setHalignment(descriptionLabel, HPos.CENTER);
    }
}
