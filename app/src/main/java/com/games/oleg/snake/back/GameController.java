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
            moveLeft();
        else if (direction == "right")
            moveRight();
        else if (direction == "up")
            moveUp();
        else if (direction == "down")
            moveDown();
    }

    public void moveLeft() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getX() - 1, headPosition.getY());
        moveIfFree(newHeadPosition);

    }

    public void moveRight() {

    }

    public void moveUp() {

    }

    public void moveDown() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getX(), headPosition.getY()+1);
        moveIfFree(newHeadPosition);
    }

    private void moveIfFree(Position futureHeadPosition) {
        if (!field.isInField(futureHeadPosition))
            return;

        Cell futureHeadCell = field.getCell(futureHeadPosition);
        if (futureHeadCell instanceof EmptyCell) {
            field.setNewHead(snake.getHeadPosition(), futureHeadPosition);
            //snake.se
        }


    }



    public void gameFinished() {

    }





}
