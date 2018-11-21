package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

public class GunPowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public GunPowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpGun"));
        double preX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double preY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        if ((preX < Globals.WINDOW_WIDTH) && preX != 0) {
            setX(preX);
        }
        if ((preY < Globals.WINDOW_HEIGHT) && preY != 0) {
            setY(preY);
        }
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            System.out.println(Snake.getAmmo());
            destroy();
        }

    }

    @Override
    public String getMessage() {
        return "Let's shoot something!";
    }
}
