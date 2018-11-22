package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.sun.javafx.geom.Vec2d;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.sql.SQLOutput;
import java.util.*;

public class GameLoop {
    private Snake snake;
    private boolean running = false;

    public GameLoop(Snake snake) {
        this.snake = snake;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if (running) {
            snake.step();
            if (Math.random() < 0.002) {

                Game.spawnEnemies(1);
            }
            if (Math.random() < 0.001) {
                Game.spawnGuns(1);
            }
            if (Math.random() < 0.003) {
                Game.spawnSpeedUps(1);
            }
            if (Math.random() < 0.002) {
                Game.spawnPowerUps(1);
            }

            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            if (Snake.getHealth() == 66){
                Game.spawnHealthBar("healthBar2");
            }
            if(Snake.getHealth() == 33){
                Game.spawnHealthBar("healthBar3");
            }
            checkCollisions();

        }

        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable) {
                        if (objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())) {
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }

    private static List<GameEntity> getSnakeParts() {
        List<GameEntity> gameObjects = Globals.getInstance().display.getObjectList();
        List<GameEntity> snakeParts = new ArrayList<>();
        for (GameEntity gameObject : gameObjects) {
            String convertedToString = String.valueOf(gameObject);
            if (convertedToString.contains("Snake")) {
                snakeParts.add(gameObject);
            }
        }
        return snakeParts;
    }

    private static List<Vec2d> getSnakePartsPositions() {
        List<GameEntity> snakeParts = getSnakeParts();
        List<Vec2d> currentPositions = new ArrayList<>();
        for (GameEntity snakePart : snakeParts) {
            currentPositions.add(snakePart.getPosition());
        }
        return currentPositions;
    }

    public static List<Double> getSnakesHead() {
        List<Vec2d> snakeParts = getSnakePartsPositions();
        List<Double> coordinates = new ArrayList<>();
        List<Vec2d> snakeHead = new ArrayList<>();
        if(snakeParts.size() != 0) {
            snakeHead.add(snakeParts.get(0));
            for (Vec2d coordinate : snakeHead) {
                coordinates.add(coordinate.x);
                coordinates.add(coordinate.y);
            }
            return coordinates;
        }
        return null;
    }

    public static boolean doNotSpawnRange(Double x, Double y) {
        List<Double> coordinates = getSnakesHead();
        if (coordinates != null) {
            Double minx = coordinates.get(0) - 100;
            Double miny = coordinates.get(1) - 600;
            Double maxx = coordinates.get(0) + 600;
            Double maxy = coordinates.get(1) + 600;
            return (x < minx && x > maxx) || (y < miny && y > maxy);
        }
        return false;
    }

}