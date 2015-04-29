package com.games.oleg.snake.back.models.cells;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by oleg on 26.10.14.
 */
public class Cell{
    private CellType cellType;
    private Drawable cellDrawable;
    protected CellOrientation cellOrientation;
    protected Resources resources;

    public Cell(CellType cellType) {
        this.cellType = cellType ;
        this.cellOrientation = CellOrientation.Invariant;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    public void drawCell(Canvas canvas, Rect bounds) {}

    public CellOrientation getCellOrientation() {
        return cellOrientation;
    }

    public void setCellOrientation(CellOrientation cellOrientation) {
        this.cellOrientation = cellOrientation;
    }

}
