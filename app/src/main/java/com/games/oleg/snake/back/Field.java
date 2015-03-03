package com.games.oleg.snake.back;

import com.games.oleg.snake.back.cells.BodyCell;
import com.games.oleg.snake.back.cells.Cell;
import com.games.oleg.snake.back.cells.EmptyCell;
import com.games.oleg.snake.back.cells.FinishCell;
import com.games.oleg.snake.back.cells.HeadCell;
import com.games.oleg.snake.back.cells.ICell;
import com.games.oleg.snake.back.cells.ObstacleCell;
import com.games.oleg.snake.back.cells.StartCell;
import com.games.oleg.snake.back.exceptions.OutOfFieldException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oleg on 26.10.14.
 */
public class Field {
    private Cell [][] grid;
    private StartCell start;
    private FinishCell finish;
    private int sizeX;
    private int sizeY;

    public Field(int size) {
        this.sizeX = size;
        this.sizeY = size;
        this.grid = new Cell[sizeY][sizeX];
        for (int i=0; i<sizeY; i++){
            for (int j=0;j<sizeX; j++){
                grid[i][j] = new EmptyCell(i, j);
            }
        }
        Position startPosition = new Position(0,0);
        this.start = new StartCell(startPosition);
        grid[startPosition.getY()][startPosition.getX()] = new StartCell(startPosition);

        Position finishPosition = new Position(size-1,size-1);
        this.finish = new FinishCell(finishPosition);
        grid[finishPosition.getY()][finishPosition.getX()] = new FinishCell(finishPosition);
    }

    public Field(int sizeX, int sizeY, int startX, int startY,
                 int finishX, int finishY, ArrayList<Position> obstacles) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.grid = new Cell[sizeY][sizeX];
        this.grid = new Cell[sizeY][sizeX];
        for (int i=0; i<sizeY; i++){
            for (int j=0;j<sizeX; j++){
                grid[i][j] = new EmptyCell(i, j);
            }
        }

        Position startPosition = new Position(startX, startY);
        this.start = new StartCell(startPosition);
        grid[startPosition.getY()][startPosition.getX()] = new StartCell(startPosition);

        Position finishPosition = new Position(finishX, finishY);
        this.finish = new FinishCell(finishPosition);
        grid[finishPosition.getY()][finishPosition.getX()] = new FinishCell(finishPosition);

        SetCellToPositions(new ObstacleCell(0,0), obstacles );
    }

    public void SetCellToPositions(Cell cellType, List<Position> positions) {
        for (Iterator<Position> p = positions.iterator(); p.hasNext();) {
            Position position = p.next();
            grid[position.getY()][position.getX()] = cellType;
        }
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public StartCell getStart() {
        return this.start;
    }

    public FinishCell getFinish() {
        return this.finish;
    }

    public boolean isInField(Position position) {
        if ( (position.getX() <= sizeX) && (position.getY()<=sizeY) )
            return true;
        else
            return false;
    }

    public Cell getCell(Position position) {
        int x = position.getX();
        int y = position.getY();
        if (x >= sizeX)
            return new ObstacleCell(x,y);
        else  if (y >= sizeY)
            return new ObstacleCell(x,y);

        return grid[x][y];
    }

    public void setNewHead(Position oldHeadPosition, Position newHeadPosition) {
        this.grid[oldHeadPosition.getX()][oldHeadPosition.getY()] = new BodyCell(oldHeadPosition);
        this.grid[newHeadPosition.getX()][newHeadPosition.getY()] = new HeadCell(newHeadPosition);
    }



}
