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
public class BodyCell extends Cell {
    private Drawable cellDrawable;

    public BodyCell(Context context, CellType cellType, CellOrientation cellOrientation) {
        super(cellType);
        this.resources = context.getResources();
        this.cellOrientation = cellOrientation;
    }

    public void drawCell(Canvas canvas, Rect bounds) {
        Bitmap bitmapToDraw;
        bitmapToDraw = DrawableController.getBodyBitmap(cellOrientation, resources);
        cellDrawable = new BitmapDrawable(resources, bitmapToDraw);
        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
