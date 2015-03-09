package com.games.oleg.snake.back.models;

import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oleg on 26.10.14.
 */
public class Field {
    private Cell [][] grid;
    private Position startPosition;
    private Position finishPosition;
    private int sizeX;
    private int sizeY;

    public Field(int size) {
        this.sizeX = size;
        this.sizeY = size;
        this.grid = new Cell[sizeY][sizeX];
        for (int i=0; i<sizeY; i++){
            for (int j=0;j<sizeX; j++){
                grid[i][j] = new Cell(CellType.EmptyCell);
            }
        }

        Position startPosition = new Position(0,0);
        this.startPosition =startPosition;
        grid[startPosition.getY()][startPosition.getX()] = new Cell(CellType.StartCell);

        Position finishPosition = new Position(size-1,size-1);
        this.finishPosition = finishPosition;
        grid[finishPosition.getY()][finishPosition.getX()] = new Cell(CellType.FinishCell);
    }

    public Field(int sizeX, int sizeY, int startX, int startY,
                 int finishX, int finishY, ArrayList<Position> obstacles) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.grid = new Cell[sizeY][sizeX];
        this.grid = new Cell[sizeY][sizeX];
        for (int i=0; i<sizeY; i++){
            for (int j=0;j<sizeX; j++){
                grid[i][j] = new Cell(CellType.EmptyCell);
            }
        }

        this.startPosition = new Position(startX, startY);
        grid[startY][startX] = new Cell(CellType.StartCell);

        this.finishPosition = new Position(finishX, finishY);
        grid[finishY][finishX] = new Cell(CellType.FinishCell);

        SetCellToPositions(CellType.ObstacleCell , obstacles );
    }

    public void SetCellToPositions(CellType cellType, List<Position> positions) {
        for (Iterator<Position> p = positions.iterator(); p.hasNext();) {
            Position position = p.next();
            grid[position.getY()][position.getX()] = new Cell(CellType.ObstacleCell);
        }
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public Position getStartPosition() {
        return this.startPosition;
    }

    public Position getFinishPosition() {
        return this.finishPosition;
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
            return new Cell(CellType.ObstacleCell);
        else  if (y >= sizeY)
            return new Cell(CellType.ObstacleCell);

        return grid[x][y];
    }

    public void setNewHead(Position oldHeadPosition, Position newHeadPosition) {
        this.grid[oldHeadPosition.getY()][oldHeadPosition.getX()] = new Cell(CellType.BodyCell);
        this.grid[newHeadPosition.getY()][newHeadPosition.getX()] = new Cell(CellType.HeadCell);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }




}
