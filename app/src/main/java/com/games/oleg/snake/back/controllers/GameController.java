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


    private void readLevel(int number)
    {

    }


    public boolean isGameFinished() {
        return (isAllFieldCovered() && isOnFinish());
    }

    private boolean isOnFinish() {
        if ( this.getSnake().getHeadPosition().isEqualTo(this.getField().getFinishPosition()) )
            return true;
        else
            return false;
    }

    private boolean isAllFieldCovered() {
        boolean hasEmpty = false;

        for (int i = 0; i <this.getField().getSizeY(); i++) {
            for (int j = 0; j < this.getField().getSizeX(); j++) {
                hasEmpty = getField().getGrid()[j][i].getCellType() == CellType.EmptyCell;
                if (hasEmpty) {
                    return false;
                }
            }
        }
        return true;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Field getField() {
        return this.field;
    }

    private boolean moveIfFree(Position futureHeadPosition) {
        if (!field.isInField(futureHeadPosition))
            return false;

        if (isPositionFree(futureHeadPosition) ) {
            field.setNewHead(snake.getHeadPosition(), futureHeadPosition);
            snake.setHead(futureHeadPosition);

            return true;
        }

        return false;
    }

    private boolean isPositionFree(Position position) {
        CellType cellType = field.getCell(position).getCellType();
        if (cellType == CellType.EmptyCell || cellType == CellType.FinishCell) {
            return true;
        }

        return false;
    }

    public boolean moveBackIfBody(Position positionToMove) {
        if (!isPositionNearHeadAndBody(positionToMove))
            return false;
        field.moveBackForHead(getSnake().getHeadPosition(), positionToMove);
        snake.moveBackForHead();

        return true;
    }

    public boolean moveToPosition(Position positionToMove) {
        if (!isPositionNearHeadAndEmpty(positionToMove))
            return false;
        field.setNewHead(getSnake().getHeadPosition(), positionToMove);
        snake.setHead(positionToMove);

        return true;
    }

    private boolean isPositionNearHeadAndEmpty(Position positionToMove) {
        return ( isPositionNearHead(positionToMove) && isPositionFree(positionToMove) );
    }

    private boolean isPositionNearHeadAndBody(Position positionToMove) {
        return ( isPositionNearHead(positionToMove) && isPositionBody(positionToMove) );

    }

    private boolean isPositionNearHead(Position positionToMove) {
        Position headPosition = this.getSnake().getHeadPosition();
        int absX = (Math.abs(positionToMove.getX() - headPosition.getX()));
        int absY = (Math.abs(positionToMove.getY() - headPosition.getY()));
        Position absPosition = new Position(absX, absY);

        if (absPosition.isEqualTo(new Position(1,0)))
            return true;

        if (absPosition.isEqualTo(new Position(0,1)))
            return true;

        return false;
    }

    private boolean isPositionBody (Position positionToMove) {
        Position lastBodyPosition = this.getSnake().getLastBodyPosition();
        if (positionToMove.isEqualTo(lastBodyPosition))
            return true;
        return false;
    }

}
