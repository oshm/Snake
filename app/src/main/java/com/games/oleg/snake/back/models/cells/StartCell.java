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
 * Created by oleg on 05.04.15.
 */
public class StartCell extends Cell {
    private Drawable cellDrawable;
    private int state;
    private final int HOLE_EMPTY = 0;
    private final int HOLE_WITH_EYES = 1;

    public StartCell(CellType cellType, Context context) {
        super(cellType);
        state = HOLE_EMPTY;
        resources = context.getResources();
        Bitmap cellBitmap = DrawableController.getStartBitmap(resources, state);
        cellDrawable = new BitmapDrawable(context.getResources(),cellBitmap);
    }

    public void setState(int state) {
        this.state = state;
        Bitmap cellBitmap = DrawableController.getStartBitmap(resources, state);
        cellDrawable = new BitmapDrawable(this.resources,cellBitmap);
    }

    public int getState() {
        return state;
    }

    public void drawCell(Canvas canvas, Rect bounds) {

        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
