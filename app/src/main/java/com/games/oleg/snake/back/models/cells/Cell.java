package com.games.oleg.snake.back.models.cells;

import android.graphics.drawable.Drawable;

/**
 * Created by oleg on 26.10.14.
 */
public class Cell{
    private CellType cellType;
    private CellOrientation cellOrientation;
    private Drawable cellDrawable;

    public Cell(CellType cellType, CellOrientation cellOrientation, Drawable cellDrawable) {
        this.cellType = cellType ;
        this.cellOrientation = cellOrientation;
        this.cellDrawable = cellDrawable;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellOrientation getCellOrientation() {
        return cellOrientation;
    }

    public void setCellOrientation(CellOrientation newCellOrientation) {
        this.cellOrientation = newCellOrientation;
    }


}
