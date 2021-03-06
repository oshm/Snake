package com.games.oleg.snake.back.models.cells;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.games.oleg.snake.back.controllers.DrawableController;

/**
 * Created by oleg on 05.04.15.
 */
public class StartCell extends Cell {
    private Drawable cellDrawable;
    private int eyesVisible;
    private final int HOLE_EMPTY = 0;
    private final int HOLE_WITH_EYES = 1;


    public StartCell(CellType cellType, Context context) {
        super(cellType);
        eyesVisible = HOLE_EMPTY;
        resources = context.getResources();
        Bitmap cellBitmap = DrawableController.getStartBitmap(eyesVisible, cellOrientation);
        cellDrawable = new BitmapDrawable(context.getResources(),cellBitmap);
    }

    public void setEyesVisible(int areVisible) {
        this.eyesVisible = areVisible;
        Bitmap cellBitmap = DrawableController.getStartBitmap(eyesVisible, cellOrientation);
        cellDrawable = new BitmapDrawable(this.resources,cellBitmap);
    }

    public int getEyesVisible() {
        return eyesVisible;
    }

    public void drawCell(Canvas canvas, Rect bounds) {

        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }

    public void setCellOrientation(CellOrientation cellOrientation) {
        super.setCellOrientation(cellOrientation);
        Bitmap cellBitmap = DrawableController.getStartBitmap(this.eyesVisible, cellOrientation);
        cellDrawable = new BitmapDrawable(this.resources,cellBitmap);
    }
}
