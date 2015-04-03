package com.games.oleg.snake.back.controllers;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by oleg.shlemin on 03.04.2015.
 */
public class DrawController {
    public void CutBitmap(Bitmap bitmap,int m, int horizParts) {
        int horizSize = bitmap.getWidth()/horizParts;
        int vertSize = bitmap.getHeight()/vertParts;

        Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());


    }
}
