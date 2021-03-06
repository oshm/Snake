package com.games.oleg.snake.back.controllers;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.games.oleg.snake.GameView;
import com.games.oleg.snake.NewGameView;
import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.models.FieldParameters;
import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.Snake;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellOrientation;
import com.games.oleg.snake.back.models.cells.CellType;
import com.games.oleg.snake.back.threads.GameLoopThread;


/**
 * Created by oleg on 28.10.14.
 */
public class GameController {
    GameLoopThread gameLoopThread;
    private Snake snake;
    private Field field;
    private XmlParser xmlParser = new XmlParser();

    private GameController() {}

    private static class GameControllerHolder {
        private static final GameController INSTANCE = new GameController();
    }

    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }

    public void startGame(XmlResourceParser level, Context context) {
        try {
            FieldParameters parameters = xmlParser.parse(level);
            //DrawableController.initImages(context.getResources());
            DrawableController.initSpriteSheets(context.getResources());
            this.field = new Field(context, parameters);

        } catch (Exception ex) {
            //TODO exception
        }

        this.snake = new Snake(this.field.getStartPosition());
    }

    public boolean isLevelFinished(Position positionToMove) {
        return (isAllFieldCovered() && isOnFinish(positionToMove));
    }

    public boolean isOnFinish(Position positionToMove) {
        if ( positionToMove.isEqualTo(this.getField().getFinishPosition()) )
            return true;
        else
            return false;
    }

    public boolean isOnStart() {
        Position startPosition = this.getField().getStartPosition();
        Position headPosition = this.getSnake().getHeadPosition();

        return headPosition.isEqualTo(startPosition);
    }

    public void computeCurrentStartOrientation() {
        if (isOnStart()) {
            getField().updateStartOrientation(CellOrientation.Invariant);
            return;
        }

        if (getSnake().getSnakeLen()==1) {
            Position headPosition = getSnake().getHeadPosition();
            CellOrientation headOrientation = this.getField().
                    getCell(headPosition).getCellOrientation();
            getField().updateStartOrientation(headOrientation);

            /*
            Position firstBodyPosition = this.getSnake().getFirstBodyPosition();
            CellOrientation firstBodyOrientation = this.getField().
                    getCell(firstBodyPosition).getCellOrientation();
            if (getField().getStartCell().getCellOrientation() != firstBodyOrientation)
                getField().updateStartOrientation(firstBodyOrientation);
                */
        }
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

    private boolean isPositionFree(Position position) {
        CellType cellType = field.getCell(position).getCellType();
        if (cellType == CellType.EmptyCell || cellType == CellType.FinishCell) {
            return true;
        }

        return false;
    }

    public boolean moveBackIfBody(Context context, Position positionToMove) {
        if (!isPositionNearHeadAndBody(positionToMove))
            return false;
        CellOrientation headOrientation = computeHeadOrientation(
                getSnake().getPreLastBodyPosition(), getSnake().getLastBodyPosition());
        field.moveBackForHead(context, getSnake().getHeadPosition(), positionToMove, headOrientation);
        snake.moveBackForHead();

        return true;
    }

    public boolean moveToPosition(Context context, Position positionToMove) {
        if (!isPositionNearHeadAndEmpty(positionToMove))
            return false;
        CellOrientation headOrientation = computeHeadOrientation(
                getSnake().getHeadPosition(), positionToMove);
        field.setNewHead(context, getSnake().getHeadPosition(), positionToMove, headOrientation);
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

    private CellOrientation computeHeadOrientation(Position moveFrom, Position moveTo) {
        int difX = moveTo.getX() - moveFrom.getX();
        int difY = moveTo.getY() - moveFrom.getY();
        Position difPosition = new Position(difX, difY);
        if (difPosition.isEqualTo(new Position(1,0)))
            return CellOrientation.Right;

        if (difPosition.isEqualTo(new Position(-1,0)))
            return CellOrientation.Left;

        if (difPosition.isEqualTo(new Position(0,1)))
            return CellOrientation.Down;

        if (difPosition.isEqualTo(new Position(0,-1)))
            return CellOrientation.Up;

        return CellOrientation.Down;
    }
}
