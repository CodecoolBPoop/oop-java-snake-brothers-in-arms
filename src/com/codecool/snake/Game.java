package com.codecool.snake;

import com.codecool.snake.entities.enemies.GunEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(1);
        spawnGunEnemies(1);
        spawnPowerUps(0);
        spawnSpeedUps(0);
        spawnGuns(1);
        spawnRockets(1);
        spawnHealthBar("healthBar");

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    static void spawnSpeedUps(int numOfPowerUps) {
        for(int i = 0; i < numOfPowerUps; ++i) new SpeedUpPowerUp();
    }

    static void spawnGuns(int numOfPowerUps) {
        for(int i = 0; i < numOfPowerUps; ++i) new GunPowerUp();
    }

    static void spawnRockets(int numOfPowerUps) {
        for(int i = 0; i < numOfPowerUps; ++i) new RocketPowerUp();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    static void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    static void spawnGunEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new GunEnemy();
    }

    static void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    public static void spawnHealthBar(String healthBar){
        int x = 100;
       int y = 30;
       new HealthBar(x, y, healthBar);
    }

    public static void spawnGameOver(){
        new GameOver();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

}
