package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class SimulationApp extends Application {
    public void start(Stage primaryStage) throws Exception{

        List<String> args = getParameters().getRaw();


        if (args.contains("--grassCount")) {
            int grassCount = Integer.parseInt(args.get(args.indexOf("--grassCount") + 1));
            WorldMap worldMap = new GrassField(grassCount);
            Simulation simulation = new Simulation(List.of(new Vector2d(0, 0)), new ArrayList<>(), worldMap);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/simulation.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();
            presenter.setWorldMap(worldMap);

            configureStage(primaryStage, viewRoot);
            primaryStage.show();
        } else {
            System.err.println("Brak wymaganych argumentów.");
        }
    }

    private WorldMap initializeWorldMap() {
        int initialGrassCount = 20;
        WorldMap worldMap = new GrassField(initialGrassCount);
        return worldMap;
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        Scene scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
