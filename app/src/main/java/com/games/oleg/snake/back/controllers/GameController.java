package com.games.oleg.snake.back.controllers;

import android.content.res.XmlResourceParser;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.Snake;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellType;


/**
 * Created by oleg on 28.10.14.
 */
public class GameController {
    private Snake snake;
    private Field field;
    private int size = 8;
    private XmlParser xmlParser = new XmlParser();

    public void startGame(XmlResourceParser level) {
        //this.field = new Field(size);

        try {
            this.field = xmlParser.parse(level);

        } catch (Exception ex) {
            //
        }

        this.snake = new Snake(this.field.getStartPosition());
    }


    private void readLevel(String xml)
    {

    }


    public boolean isGameFinished() {
        if (this.getSnake().getHeadPosition() == this.getField().getFinishPosition())
            return true;
        else
            return false;
    }

    public void finishGame()
    {
        System.out.println("Game finished!");
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
        Position newHeadPosition = new Position(headPosition.getX()-1, headPosition.getY());
        moveIfFree(newHeadPosition);

    }

    public void moveRight() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getX()+1, headPosition.getY());
        moveIfFree(newHeadPosition);
    }

    public void moveUp() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getX(), headPosition.getY()-1);
        moveIfFree(newHeadPosition);
    }

    public boolean moveDown() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getX(), headPosition.getY()+1);
        return moveIfFree(newHeadPosition);
    }

    private boolean moveIfFree(Position futureHeadPosition) {
        if (!field.isInField(futureHeadPosition))
            return false;

        Cell futureHeadCell = field.getCell(futureHeadPosition);
        if (futureHeadCell.getCellType() == CellType.EmptyCell ) {
            field.setNewHead(snake.getHeadPosition(), futureHeadPosition);
            snake.setHead(futureHeadPosition);

            return true;
        }

        return false;
    }





}
