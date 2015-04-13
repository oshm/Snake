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
    private int drawableId = R.drawable.start;
    private Drawable cellDrawable;

    public StartCell(CellType cellType, Context context) {
        super(cellType);
        Bitmap cellBitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        cellBitmap = DrawableController.makeBitmapTransparent(cellBitmap, Color.WHITE);
        cellDrawable = new BitmapDrawable(context.getResources(),cellBitmap);
    }

    public void drawCell(Canvas canvas, Rect bounds) {
        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
