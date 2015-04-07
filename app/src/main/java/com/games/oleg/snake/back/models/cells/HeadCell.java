package com.games.oleg.snake.back.models.cells;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.games.oleg.snake.R;
import com.games.oleg.snake.back.models.SpritesSheet;

/**
 * Created by oleg on 05.04.15.
 */
public class HeadCell extends Cell {
    private int drawableId = R.drawable.snake;
    private Bitmap cellBitmap;
    private Drawable cellDrawable;
    private Resources resources;

    public HeadCell(Context context, CellType cellType, CellOrientation cellOrientation) {
        super(cellType);
        this.cellOrientation = cellOrientation;
        this.resources = context.getResources();
        SpritesSheet snakeSheet = new SpritesSheet(context, R.drawable.snake, 2, 1);
        cellBitmap = snakeSheet.getBitmapFromPosition(1, 1);
        cellBitmap = super.makeBitmapTransparent(cellBitmap, Color.WHITE);
        cellBitmap = super.rotateBitmap(cellBitmap, 90);
        cellDrawable = new BitmapDrawable(context.getResources(), cellBitmap);
    }

    public void drawCell(Canvas canvas, Rect bounds) {
        Bitmap bitmapToDraw;
        bitmapToDraw = super.rotateBitmap(cellBitmap, cellOrientation);
        cellDrawable = new BitmapDrawable(resources, bitmapToDraw);
        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
