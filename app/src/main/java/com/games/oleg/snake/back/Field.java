package com.games.oleg.snake.back;

import com.games.oleg.snake.back.cells.BodyCell;
import com.games.oleg.snake.back.cells.Cell;
import com.games.oleg.snake.back.cells.FinishCell;
import com.games.oleg.snake.back.cells.HeadCell;
import com.games.oleg.snake.back.cells.StartCell;
import com.games.oleg.snake.back.exceptions.OutOfFieldException;

/**
 * Created by oleg on 26.10.14.
 */
public class Field {
    private Cell [][] grid;
    private StartCell start;
    private FinishCell finish;
    private int size;

    public Field(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        for (int i=0; i<size; i++){
            for (int j=0;j<size; j++){
                grid[i][j] = new Cell(i, j);
            }
        }

        grid[0][0] = new StartCell(0,0);
        grid[size][size] = new FinishCell(size, size);
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
        if ( (position.getX() <= size) && (position.getY()<=size) )
            return true;
        else
            return false;
    }

    public Cell getCell(Position position) throws OutOfFieldException{
        int x = position.getX();
        int y = position.getY();
        if (x >= size)
            throw new OutOfFieldException(x, size, "X");
        else  if (y >= size)
            throw new OutOfFieldException(y, size, "Y");

        return grid[x][y];
    }

    public void setNewHead(Position oldHeadPosition, Position newHeadPosition) {
        this.grid[oldHeadPosition.getX()][oldHeadPosition.getY()] = new BodyCell(oldHeadPosition);
        this.grid[newHeadPosition.getX()][newHeadPosition.getY()] = new HeadCell(newHeadPosition);
    }



}
