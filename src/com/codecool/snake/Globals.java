package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeHead1", new Image("New_Project_5.png"));
        resources.addImage("SnakeHeadGun", new Image("gunman.png"));
        resources.addImage("SnakeBody", new Image("New_Project_5.png"));
        resources.addImage("SimpleEnemy", new Image("btank.png"));
        resources.addImage("GunEnemy", new Image("Enemy_2.png"));
        resources.addImage("PowerUpGun", new Image("agun1.png"));
        resources.addImage("PowerUpRocket", new Image("rocket_gun.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("PowerUpSpeed", new Image("test_powerup.png"));
        resources.addImage("healthBar", new Image("healthBar.png"));
        resources.addImage("healthBar2", new Image("healthBar2.png"));
        resources.addImage("healthBar3", new Image("healthBar3.png"));
        resources.addImage("gameOver", new Image("gameOver.png"));
        resources.addImage("Ammo", new Image("ammo.png"));
        resources.addImage("rocketAmmo", new Image("rocket_ammo.png"));
        resources.addImage("Background", new Image("com/codecool/snake/wbackground.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
