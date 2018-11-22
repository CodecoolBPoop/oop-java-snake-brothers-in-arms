package com.codecool.snake.entities.enemies;

import com.codecool.snake.GameLoop;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.Shooting.RocketShooting;
import com.codecool.snake.entities.Shooting.Shooting;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class GunEnemy extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private static Random rnd = new Random();
    public static double xCoordinate;
    public static double yCoordinate;

    public GunEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("GunEnemy"));
        do {
            xCoordinate = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            yCoordinate = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        } while (GameLoop.doNotSpawnRange(xCoordinate, yCoordinate));

        setX(xCoordinate);
        setY(yCoordinate);


        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }else if (entity instanceof Shooting) {
            destroy();
        } else if (entity instanceof RocketShooting) {
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
