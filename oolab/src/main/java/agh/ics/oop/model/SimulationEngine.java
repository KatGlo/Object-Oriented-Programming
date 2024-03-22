package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> runningThreads = new ArrayList<>();

    private final List<MapChangeListener> observers =new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void addSimulationObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            runningThreads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() {
        for (Thread thread : runningThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error while waiting for simulation to end: " + e.getMessage());
            }
        }
    }

    public void runAsyncInThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("Timeout while waiting for thread pool termination");
            }
        } catch (InterruptedException e) {
            System.err.println("Error while waiting for thread pool termination: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public List<Simulation> getSimulations() {
        return simulations;
    }
}
