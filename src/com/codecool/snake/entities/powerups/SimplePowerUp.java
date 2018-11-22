package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.resources.Resources;

import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        setImage(Globals.getInstance().getImage("SnakeBody"));
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
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
