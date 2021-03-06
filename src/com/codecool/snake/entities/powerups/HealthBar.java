package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;


public class HealthBar extends GameEntity implements Interactable  {


    public HealthBar(double x, double y, String healthBar) {
        setImage(Globals.getInstance().getImage(healthBar));
        double preX = x;
        double preY = y;
            setX(preX);
            setY(preY);
    }


    @Override
    public void apply(GameEntity entity) {
        if(Snake.getHealth() == 66){
            destroy();
        }
        if(Snake.getHealth() == 33){
            destroy();
        }
        if(Snake.getHealth() == 0){
            destroy();
        }

    }

    @Override
    public String getMessage() {
        return null;
    }
}



