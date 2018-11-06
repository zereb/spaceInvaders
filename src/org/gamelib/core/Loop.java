package org.gamelib.core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public abstract class Loop extends Application {

    private int WIDTH, HEIGHT;
    private String title;
    private Stage stage;
    private float timeStep = 0, lastStepTime = 0;
    protected ArrayList<String> input = new ArrayList<String>();
    public int fps;
    private long lastFpsTime;
    protected boolean pause  = false;


    public void initLoop(int WIDTH, int HEIGHT, Stage stage, String title, double speed) {

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.title = title;
        this.stage = stage;

        stage.setTitle(title);
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            double accumulatedTime = 0;

            @Override
            public void handle(long now) {
                double time = (now - startNanoTime) / 1e9d;
                timeStep = (now - lastStepTime) / 1e9f;
                lastStepTime = System.nanoTime();


                scene.setOnKeyPressed(
                        new EventHandler<KeyEvent>() {
                            public void handle(KeyEvent e) {
                                String code = e.getCode().toString();
                                if (!input.contains(code))
                                    input.add(code);
                            }
                        });

                scene.setOnKeyReleased(
                        new EventHandler<KeyEvent>() {
                            public void handle(KeyEvent e) {
                                String code = e.getCode().toString();
                                input.remove(code);
                            }
                        });

                inputHandler();

                if(!pause){
                    if (accumulatedTime >= speed) {
                        update(accumulatedTime, time);
                        accumulatedTime = 0;
                    } else {
                        accumulatedTime += timeStep;
                    }
                }
                render(gc);
            }
        }.start();

        stage.show();
    }

    public abstract void start(Stage primaryStage) throws Exception;

    public abstract void update(double timeStep, double time);

    public abstract void render(GraphicsContext gc);

    public abstract void inputHandler();

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public float getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(float timeStep) {
        this.timeStep = timeStep;
    }

    public float getLastStepTime() {
        return lastStepTime;
    }

    public void setLastStepTime(float lastStepTime) {
        this.lastStepTime = lastStepTime;
    }
}
