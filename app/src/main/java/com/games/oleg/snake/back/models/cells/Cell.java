package com.games.oleg.snake.back.models.cells;

/**
 * Created by oleg on 26.10.14.
 */
public class Cell{
    private CellType cellType;

    public Cell(CellType cellType) {
        this.cellType = cellType ;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}
