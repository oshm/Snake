package com.games.oleg.snake.back.controllers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;

import com.games.oleg.snake.R;
import com.games.oleg.snake.back.models.cells.CellOrientation;


/**
 * Created by oleg.shlemin on 13.04.2015.
 */
public class DrawableController {
    private static  int backgroundColor = -65308;

    private static int headId = R.drawable.shead;
    private static Bitmap headBitmap;
    private static Bitmap headBitmapUp;
    private static Bitmap headBitmapDown;
    private static Bitmap headBitmapLeft;
    private static Bitmap headBitmapRight;

    private static int bodyId = R.drawable.sline;
    private static Bitmap bodyBitmap;
    private static Bitmap bodyBitmapUp;
    private static Bitmap bodyBitmapDown;
    private static Bitmap bodyBitmapLeft;
    private static Bitmap bodyBitmapRight;

    private static int turnId = R.drawable.sturn;
    private static Bitmap turnBitmap;
    private static Bitmap turnBitmapUpLeft;
    private static Bitmap turnBitmapUpRight;;
    private static Bitmap turnBitmapDownLeft;;
    private static Bitmap turnBitmapDownRight;

    private static int stoneId = R.drawable.stone01;
    private static Bitmap stoneBitmap;

    private static int startHoleId = R.drawable.hole01;
    private static int startHoleWithEyesId = R.drawable.hole02;
    private static int startDownId = R.drawable.hole03;
    private static Bitmap startHoleBitmap;
    private static Bitmap startHoleWithEyesBitmap;
    private static Bitmap startUp;
    private static Bitmap startDown;
    private static Bitmap startLeft;
    private static Bitmap startRight;


    public static Bitmap makeBitmapTransparent(Bitmap bit, int transparentColor) {
        int width =  bit.getWidth();
        int height = bit.getHeight();
        Bitmap myBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int [] allpixels = new int [ myBitmap.getHeight()*myBitmap.getWidth()];
        bit.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(),myBitmap.getHeight());
        myBitmap.setPixels(allpixels, 0, width, 0, 0, width, height);

        for(int i =0; i<myBitmap.getHeight()*myBitmap.getWidth();i++){
            if((allpixels[i] > transparentColor-10) && (allpixels[i]< transparentColor+10))

                allpixels[i] = Color.alpha(Color.TRANSPARENT);
        }

        myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
        return myBitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, CellOrientation cellOrientation) {
        float angle = cellOrientation.getNumVal();
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap rotatedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return rotatedBitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap,float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap rotatedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return rotatedBitmap;
    }

    public static Bitmap getStartBitmap(Resources resources, int eyesVisible, CellOrientation cellOrientation) {
        if (startHoleBitmap == null) {
            initStartBitmaps(resources);
        }

        switch (cellOrientation) {
            case Up: {
                return startUp;
            }
            case Down: {
                return startDown;
            }
            case Left: {
                return startLeft;
            }
            case Right: {
                return startRight;
            }
        }

        if (eyesVisible==0)
            return startHoleBitmap;
        else
            return startHoleWithEyesBitmap;

    }

    public static Bitmap getObstacleBitmap(Resources resources) {
        if (stoneBitmap == null) {
            stoneBitmap = BitmapFactory.decodeResource(resources, stoneId);
            stoneBitmap = makeBitmapTransparent(stoneBitmap, backgroundColor);
        }

        return stoneBitmap;
    }

    public static Bitmap getBodyBitmap(CellOrientation cellOrientation, Resources resources) {
        if (bodyBitmap == null) {
            bodyBitmap = BitmapFactory.decodeResource(resources, bodyId);
            bodyBitmap = makeBitmapTransparent(bodyBitmap, backgroundColor);
        }

        switch (cellOrientation) {
            case Up: {
                if (bodyBitmapUp == null)
                    bodyBitmapUp = rotateBitmap(bodyBitmap, cellOrientation);
                return bodyBitmapUp;
            }
            case Down: {
                if (bodyBitmapDown == null)
                    bodyBitmapDown = rotateBitmap(bodyBitmap, cellOrientation);
                return bodyBitmapDown;
            }
            case Left: {
                if (bodyBitmapLeft == null)
                    bodyBitmapLeft = rotateBitmap(bodyBitmap, cellOrientation);
                return bodyBitmapLeft;
            }
            case Right: {
                if (bodyBitmapRight == null)
                    bodyBitmapRight = rotateBitmap(bodyBitmap, cellOrientation);
                return bodyBitmapRight;
            }
        }

        return bodyBitmap;
    }

    public static Bitmap getHeadBitmap(CellOrientation cellOrientation, Resources resources) {
        if (headBitmap == null) {
            headBitmap = BitmapFactory.decodeResource(resources, headId);
            headBitmap = DrawableController.rotateBitmap(headBitmap, 180);
            headBitmap = makeBitmapTransparent(headBitmap, backgroundColor);
        }

        switch (cellOrientation) {
            case Up: {
                if (headBitmapUp == null)
                    headBitmapUp = rotateBitmap(headBitmap, cellOrientation);
                return headBitmapUp;
            }
            case Down: {
                if (headBitmapDown == null)
                    headBitmapDown = rotateBitmap(headBitmap, cellOrientation);
                return headBitmapDown;
            }
            case Left: {
                if (headBitmapLeft == null)
                    headBitmapLeft = rotateBitmap(headBitmap, cellOrientation);

                return headBitmapLeft;
            }
            case Right: {
                if (headBitmapRight == null)
                    headBitmapRight = rotateBitmap(headBitmap, cellOrientation);
                return headBitmapRight;
            }
        }

        return headBitmap;
    }

    public static Bitmap getTurnBitmap(Resources resources, CellOrientation bodyOrientation, CellOrientation headOrientation) {
        if (turnBitmap == null) {
            initTurnBitmaps(resources);
        }

        switch (bodyOrientation) {
            case Up: {
                if (headOrientation.equals(CellOrientation.Left))
                    return  turnBitmapDownLeft;
                else
                    return turnBitmapDownRight;
            }
            case Down: {
                if (headOrientation.equals(CellOrientation.Left))
                    return  turnBitmapUpLeft;
                else
                    return turnBitmapUpRight;
            }

            case Left: {
                if (headOrientation.equals(CellOrientation.Up))
                    return  turnBitmapUpRight;
                else
                    return turnBitmapDownRight;
            }

            case Right: {
                if (headOrientation.equals(CellOrientation.Up))
                    return  turnBitmapUpLeft;
                else
                    return turnBitmapDownLeft;
            }

            default:
                return turnBitmap;

        }

    }

    public static void initTurnBitmaps(Resources resources) {
        if (turnBitmap == null) {
            turnBitmap = BitmapFactory.decodeResource(resources, turnId);
            turnBitmap = makeBitmapTransparent(turnBitmap, backgroundColor);
        }

        if (turnBitmapDownRight == null) {
            turnBitmapDownRight = rotateBitmap(turnBitmap, 0);
        }

        if (turnBitmapDownLeft == null) {
            turnBitmapDownLeft = rotateBitmap(turnBitmap, 90);
        }

        if (turnBitmapUpLeft == null) {
            turnBitmapUpLeft = rotateBitmap(turnBitmap, 180);
        }

        if (turnBitmapUpRight == null) {
            turnBitmapUpRight = rotateBitmap(turnBitmap, 270);
        }
    }

    public static void initStartBitmaps(Resources resources) {
        if (startHoleBitmap == null) {
            startHoleBitmap = BitmapFactory.decodeResource(resources, startHoleId);
            startHoleBitmap = makeBitmapTransparent(startHoleBitmap, backgroundColor);
        }

        if (startHoleWithEyesBitmap == null) {
            startHoleWithEyesBitmap = BitmapFactory.decodeResource(resources, startHoleWithEyesId);
            startHoleWithEyesBitmap = makeBitmapTransparent(startHoleWithEyesBitmap, backgroundColor);
        }

        if (startDown == null) {
            startDown = BitmapFactory.decodeResource(resources, startDownId);
            startDown = makeBitmapTransparent(startDown, backgroundColor);
        }

        if (startUp == null) {
            startUp = rotateBitmap(startDown, 180);
        }

        if (startLeft == null) {
            startLeft = rotateBitmap(startDown, 90);
        }

        if (startRight == null) {
            startRight = rotateBitmap(startDown, 270);
        }



    }

}
