package com.games.oleg.snake.back.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by oleg.shlemin on 03.04.2015.
 */
public class SpritesSheet {
    private Bitmap sheetBitmap;
    private int verticalMax;
    private int horizontalMax;
    float oneHorizontalSize;
    float oneVerticalSize;

    public SpritesSheet(Context context, int sheetId, int horizontalMax, int verticalMax) {
        this.sheetBitmap = BitmapFactory.decodeResource(context.getResources(), sheetId);
        this.horizontalMax = horizontalMax;
        this.verticalMax = verticalMax;
        oneHorizontalSize = sheetBitmap.getWidth()/horizontalMax;
        oneVerticalSize = sheetBitmap.getHeight()/verticalMax;
    }

    public SpritesSheet(Bitmap sheetBitmap, int horizontalMax, int verticalMax) {
        this.sheetBitmap = sheetBitmap;
        this.horizontalMax = horizontalMax;
        this.verticalMax = verticalMax;
        oneHorizontalSize = sheetBitmap.getWidth()/horizontalMax;
        oneVerticalSize = sheetBitmap.getHeight()/verticalMax;
    }

    public Bitmap getBitmapFromPosition(int horizontal, int vertical) {
        float oneHorizontalSize = sheetBitmap.getWidth()/horizontalMax;
        float oneVerticalSize = sheetBitmap.getHeight()/verticalMax;

        Bitmap croppedBmp =
                Bitmap.createBitmap(sheetBitmap,
                        (int)((horizontal-1)*oneHorizontalSize),(int)((vertical-1)*oneVerticalSize),
                        (int)((horizontal)*oneHorizontalSize),(int)((vertical)*oneVerticalSize));
        return croppedBmp;
    }



}
