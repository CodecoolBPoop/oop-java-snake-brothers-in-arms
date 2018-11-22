package com.codecool.snake.entities.snakes;

import com.codecool.snake.*;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.Shooting.Shooting;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.GunPowerUp;
import com.codecool.snake.entities.powerups.HealthBar;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import com.codecool.snake.entities.powerups.SpeedUpPowerUp;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

import java.util.concurrent.TimeUnit;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;
    String snakeImage;

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead1"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }
        if (snakeImage != null && snakeImage.equals("SnakeHeadGun")) {
            if (Snake.getAmmo() != 0) {
                if (turnDirection.equals(SnakeControl.SPACE)) {
                    new Shooting(headRotation);
                }
            }
        }


        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    public void ammoCounter() {
        Snake.setAmmo(Snake.getAmmo() -1);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            Snake.setHealth(Snake.getHealth() - 33);
            }

        if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(1);
        }
        if (entity instanceof SpeedUpPowerUp) {
            Snake.setSpeed(Snake.getSpeed() + 0.3f);
        }
        if (entity instanceof GunPowerUp && Snake.getAmmo() == 0) {
            setImage(Globals.getInstance().getImage("SnakeHeadGun"));
            snakeImage = "SnakeHeadGun";
            Snake.setAmmo(Snake.getAmmo()+ 36);
            Snake.setSpeed(Snake.getSpeed() - 0.3f);
        }else if (entity instanceof GunPowerUp && Snake.getAmmo() != 0) {
            Snake.setAmmo(Snake.getAmmo() + 36);
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
