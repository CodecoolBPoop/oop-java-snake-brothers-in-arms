package com.codecool.snake.entities.Shooting;

import com.codecool.snake.GameLoop;
import com.codecool.snake.GameTimer;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.GunEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import com.sun.javafx.geom.Vec2d;

import java.util.List;

public class Shooting extends GameEntity implements Animatable, Interactable {
    private List<Double> coordinates = GameLoop.getSnakesHead();

    private Point2D heading;

    public Shooting(double rotation) {

        setImage(Globals.getInstance().getImage("Ammo"));
        setX(coordinates.get(0));
        setY(coordinates.get(1));

        double direction = rotation;
        setRotate(direction);

        int speed = 6;
        heading = Utils.directionToVector(direction, speed);
        Snake.setAmmo(Snake.getAmmo() -1);
        System.out.println("Ammo: " + Snake.getAmmo());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof GunEnemy){
            System.out.println("Shot Down");
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        assert coordinates != null;
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }
}
