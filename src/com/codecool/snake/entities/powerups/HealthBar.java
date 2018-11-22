package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;

public class HealthBar extends GameEntity implements Interactable {


    int health = Snake.getHealth();

    public HealthBar(double x, double y) {
        setImage(Globals.getInstance().getImage("healthBar"));
        double preX = x;
        double preY = y;
            setX(preX);
            setY(preY);
    }

    @Override
    public void apply(GameEntity entity) {

    }

    @Override
    public String getMessage() {
        return "Health bar";
    }
}



