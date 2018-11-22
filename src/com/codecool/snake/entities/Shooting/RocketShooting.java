package com.codecool.snake.entities.Shooting;

import com.codecool.snake.GameLoop;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.GunEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.Snake;
import javafx.geometry.Point2D;

import java.util.List;

public class RocketShooting extends GameEntity implements Animatable, Interactable {
    private List<Double> coordinates = GameLoop.getSnakesHead();

    private Point2D heading;

    public RocketShooting(double rotation) {

        setImage(Globals.getInstance().getImage("rocketAmmo"));
        setX(coordinates.get(0));
        setY(coordinates.get(1));

        double direction = rotation;
        setRotate(direction);

        int speed = 6;
        heading = Utils.directionToVector(direction, speed);
        Snake.setRocketAmmo(Snake.getRocketAmmo() -1);
        System.out.println("Rocket ammo: " +Snake.getRocketAmmo());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SimpleEnemy){
            System.out.println("Shot Down");
            destroy();
        } else if (entity instanceof GunEnemy) {
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
