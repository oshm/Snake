package com.games.oleg.snake.back.models.cells;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.games.oleg.snake.back.controllers.DrawableController;

import java.util.ArrayList;

/**
 * Created by oleg.shlemin on 13.04.2015.
 */
public class TurnCell extends Cell {
    private Drawable cellDrawable;
    private CellOrientation nextCellOrientation;

    public TurnCell(Context context, CellType cellType, CellOrientation cellOrientation,
                    CellOrientation nextCellOrientation) {
        super(cellType);
        this.resources = context.getResources();
        this.cellOrientation = cellOrientation;
        this.nextCellOrientation = nextCellOrientation;
    }

    public void drawCell(Canvas canvas, Rect bounds) {
        Bitmap bitmapToDraw;
        ArrayList<CellOrientation> turnOrientations = new ArrayList<CellOrientation>();
        turnOrientations.add(cellOrientation);
        turnOrientations.add(nextCellOrientation);
        bitmapToDraw = DrawableController.getTurnBitmap(resources, turnOrientations);
        cellDrawable = new BitmapDrawable(resources, bitmapToDraw);
        cellDrawable.setBounds(bounds);
        cellDrawable.draw(canvas);
    }
}
