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

/**
 * Created by oleg on 04.04.15.
 */
public class ObstacleCell extends Cell {
    private int drawableId = R.drawable.stone;
    private Drawable cellDrawable;

    public ObstacleCell(CellType cellType, Context context) {
        super(cellType);
        Bitmap obstacleBitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        obstacleBitmap = super.makeBitmapTransparent(obstacleBitmap, Color.WHITE);
        cellDrawable = new BitmapDrawable(context.getResources(),obstacleBitmap);
        //cellDrawable = context.getResources().getDrawable(drawableId);
    }

    public void drawCell(Canvas canvas, Rect bounds) {
        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
