package com.games.oleg.snake.back.models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by oleg.shlemin on 03.04.2015.
 */
public class SpriteSheet {
    private Bitmap sheetBitmap;
    private int verticalMax;
    private int horizontalMax;
    float oneHorizontalSize;
    float oneVerticalSize;

    public SpriteSheet(Resources resources, int sheetId, int horizontalMax, int verticalMax) {
        this.sheetBitmap = BitmapFactory.decodeResource(resources, sheetId);
        this.horizontalMax = horizontalMax;
        this.verticalMax = verticalMax;
        oneHorizontalSize = sheetBitmap.getWidth()/horizontalMax;
        oneVerticalSize = sheetBitmap.getHeight()/verticalMax;
    }

    public Bitmap getBitmapFromPosition(int horizontal, int vertical) {
        float oneHorizontalSize = sheetBitmap.getWidth() / horizontalMax;
        float oneVerticalSize = sheetBitmap.getHeight() / verticalMax;

        int x0 = (int) ((horizontal - 1) * oneHorizontalSize);
        int y0 = (int) ((vertical - 1) * oneVerticalSize);
        Bitmap croppedBmp = Bitmap.createBitmap(sheetBitmap, x0, y0,
                (int)oneHorizontalSize, (int)oneVerticalSize);

        return croppedBmp;
    }

    public void recycleSpriteSheetBitmap() {
        if (sheetBitmap != null) {
            sheetBitmap.recycle();
        }
    }



}
