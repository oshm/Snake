package com.games.oleg.snake.back;

import com.games.oleg.snake.back.cells.Cell;
import com.games.oleg.snake.back.cells.EmptyCell;
import com.games.oleg.snake.back.exceptions.OutOfFieldException;

/**
 * Created by oleg on 28.10.14.
 */
public class GameController {
    private Snake snake;
    private Field field;
    private int size = 8;


    public void startGame() {
        this.field = new Field(size);
        this.snake = new Snake(field.getStart().getPosition());
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Field getField() {
        return this.field;
    }

    public void moveSnake(String direction) {
        if (direction == "left")
            snake.moveLeft();
        else if (direction == "right")
            snake.moveRight();
        else if (direction == "up")
            snake.moveUp();
        else if (direction == "down")
            snake.moveDown();
    }

    public void moveLeft() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getX() - 1, headPosition.getY());
        

    }

    public void moveRight() {

    }

    public void moveUp() {

    }

    public void moveDown() {

    }

    private void moveIfFree(Position futureHeadPosition)
            throws OutOfFieldException{
        if (!field.isInField(futureHeadPosition))
            return;

        Cell futureHeadCell = field.getCell(futureHeadPosition);
        if (futureHeadCell instanceof EmptyCell) {
            field.setNewHead(snake.getHeadPosition(), futureHeadPosition);
            snake.se
        }


    }



    public void gameFinished() {

    }





}
