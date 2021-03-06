package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthBar;
import com.codecool.snake.eventhandler.InputHandler;


import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;


public class Snake implements Animatable {
    private static float speed = 2;
    private static int health = 99;
    private static int ammo;
    private static int rocketAmmo;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }


    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();
        body.doPendingModifications();

    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.SPACE)) turnDir = SnakeControl.SPACE;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.R)) turnDir = SnakeControl.R;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health -= diff;
    }


    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Game.spawnGameOver();
            Globals.getInstance().stopGame();




        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }

    public static int getHealth() { return health; }

    public static void setHealth(int health) { Snake.health = health; }

    public static float getSpeed() {
        return speed;
    }

    public static void setSpeed(float speed) {
        Snake.speed = speed;
    }

    public static int getAmmo() {
        return ammo;
    }

    public static void setAmmo(int ammo) {
        Snake.ammo = ammo;
    }

    public static int getRocketAmmo() {
        return rocketAmmo;
    }

    public static void setRocketAmmo(int rocketAmmo) {
        Snake.rocketAmmo = rocketAmmo;
    }
}