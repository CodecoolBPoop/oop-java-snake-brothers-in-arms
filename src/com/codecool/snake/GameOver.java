package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;

public class GameOver extends GameEntity  {

    GameOver(){
        setImage(Globals.getInstance().getImage("gameOver"));
        double preX = 150;
        double preY = 100;
        setX(preX);
        setY(preY);
    }
    }