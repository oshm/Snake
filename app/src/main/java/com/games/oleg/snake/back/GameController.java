package com.games.oleg.snake.back;

import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.games.oleg.snake.back.cells.Cell;
import com.games.oleg.snake.back.cells.EmptyCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Created by oleg on 28.10.14.
 */
public class GameController {
    private Snake snake;
    private Field field;
    private int size = 8;
    private XmlParser xmlParser = new XmlParser();

    public void startGame(XmlResourceParser level) {
        /*
        InputStream stream = null;

        try {
            FileInputStream fis = new FileInputStream("res//xml//level.xml");
        } catch (FileNotFoundException ex) {
            //
            String foo="";
        }
*/
        try {
            xmlParser.parse(level);

        } catch (Exception ex) {
            //
        }

        this.field = new Field(size);
        this.snake = new Snake(field.getStart().getPosition());
    }


    private void readLevel(String xml)
    {

    }


    public boolean isGameFinished() {
        if (this.getSnake().getHeadPosition() == this.getField().getFinish().getPosition())
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
        Position newHeadPosition = new Position(headPosition.getY(), headPosition.getX()-1);
        moveIfFree(newHeadPosition);

    }

    public void moveRight() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getY(), headPosition.getX()+1);
        moveIfFree(newHeadPosition);
    }

    public void moveUp() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getY()-1, headPosition.getX());
        moveIfFree(newHeadPosition);
    }

    public void moveDown() {
        Position headPosition = snake.getHeadPosition();
        Position newHeadPosition = new Position(headPosition.getY()+1, headPosition.getX());
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





}
