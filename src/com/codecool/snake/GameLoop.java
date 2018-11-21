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
            if (Math.random() < 0.007) {
                // add here the exception for enemy spawn on the snake body
                getSnakePartsPositions();
                Game.spawnEnemies(1);
            }
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
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

    private List<GameEntity> getSnakeParts() {
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


    private void getSnakePartsPositions() {
        List<GameEntity> snakeParts = getSnakeParts();
        List<Vec2d> currentPositions = new ArrayList<>();
        for (GameEntity snakePart : snakeParts) {
            currentPositions.add(snakePart.getPosition());
        }
        System.out.println(currentPositions);
    }
}