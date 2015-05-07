package com.games.oleg.snake.back.models.cells;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.games.oleg.snake.R;
import com.games.oleg.snake.back.controllers.DrawableController;

/**
 * Created by oleg on 04.04.15.
 */
public class ObstacleCell extends Cell {
    private Drawable cellDrawable;

    public ObstacleCell(CellType cellType, Context context) {
        super(cellType);
        this.resources = context.getResources();
        Bitmap cellBitmap = DrawableController.getObstacleBitmap();
        cellDrawable = new BitmapDrawable(resources, cellBitmap);
    }

    public void drawCell(Canvas canvas, Rect bounds) {
        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
