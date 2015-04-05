package com.games.oleg.snake.back.models;

import android.graphics.Bitmap;

/**
 * Created by oleg.shlemin on 03.04.2015.
 */
public class SpritesSheet {
    private Bitmap sheetBitmap;
    private int verticalMax;
    private int horizontalMax;
    float oneHorizontalSize;
    float oneVerticalSize;

    public SpritesSheet(Bitmap sheetBitmap, int verticalMax, int horizontalMax) {
        this.sheetBitmap = sheetBitmap;
        this.horizontalMax = horizontalMax;
        this.verticalMax = verticalMax;
        oneHorizontalSize = sheetBitmap.getWidth()/horizontalMax;
        oneVerticalSize = sheetBitmap.getHeight()/verticalMax;
    }

    public Bitmap getBitmapFromPosition(int vertical, int horizontal) {
        float oneHorizontalSize = sheetBitmap.getWidth()/horizontalMax;
        float oneVerticalSize = sheetBitmap.getHeight()/verticalMax;

        Bitmap croppedBmp =
                Bitmap.createBitmap(sheetBitmap,
                        (int)((horizontal-1)*oneHorizontalSize),(int)((vertical-1)*oneVerticalSize),
                        (int)((horizontal)*oneHorizontalSize),(int)((vertical)*oneVerticalSize));
        return croppedBmp;
    }



}
