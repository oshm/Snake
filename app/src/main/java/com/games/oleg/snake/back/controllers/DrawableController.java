package com.games.oleg.snake.back.controllers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

import com.games.oleg.snake.R;
import com.games.oleg.snake.back.models.SpriteSheet;
import com.games.oleg.snake.back.models.cells.CellOrientation;

import java.util.HashMap;


/**
 * Created by oleg.shlemin on 13.04.2015.
 */
public class DrawableController {
    //private static  int backgroundColor = -1376035;
    private static int backgroundColor = Color.rgb(236, 0, 217);

    private static final int spriteSheet1Id = R.drawable.spritesheet1;
    private static final int spriteSheet2Id = R.drawable.spritesheet2;
    private static SpriteSheet spriteSheet1;
    private static SpriteSheet spriteSheet2;

    private static Bitmap emptyTransparentBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    private static Bitmap turnBitmap;
    private static Bitmap turnBitmapUpLeft;
    private static Bitmap turnBitmapUpRight;
    private static Bitmap turnBitmapDownLeft;
    private static Bitmap turnBitmapDownRight;

    private static Bitmap stoneBitmap;

    private static Bitmap startHoleBitmap;
    private static Bitmap startHoleWithEyesBitmap;
    private static Bitmap startUp;
    private static Bitmap startDown;
    private static Bitmap startLeft;
    private static Bitmap startRight;

    private static HashMap<CellOrientation, Bitmap[]> headBitmaps;
    private static Bitmap[] headBitmapsDown;
    private static Bitmap[] headBitmapsUp;
    private static Bitmap[] headBitmapsLeft;
    private static Bitmap[] headBitmapsRight;

    private static HashMap<CellOrientation, Bitmap[]> bodyBitmaps;
    private static Bitmap[] bodyBitmapsDown;
    private static Bitmap[] bodyBitmapsUp;
    private static Bitmap[] bodyBitmapsLeft;
    private static Bitmap[] bodyBitmapsRight;



    private static Bitmap[] headMovedBitmaps;


    public static Bitmap makeBitmapTransparent(Bitmap bit, int transparentColor) {
        int width =  bit.getWidth();
        int height = bit.getHeight();
        Bitmap myBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int [] allpixels = new int [ myBitmap.getHeight()*myBitmap.getWidth()];
        bit.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(),myBitmap.getHeight());
        myBitmap.setPixels(allpixels, 0, width, 0, 0, width, height);
        for(int i =0; i<myBitmap.getHeight()*myBitmap.getWidth();i++){
            boolean isRedNear = Math.abs(Color.red(transparentColor) - Color.red(allpixels[i])) < 20;
            boolean isGreenNear = Math.abs(Color.green(transparentColor) - Color.green(allpixels[i])) < 20;
            boolean isBlueNear = Math.abs(Color.blue(transparentColor) - Color.blue(allpixels[i])) < 20;
            if (isRedNear && isGreenNear && isBlueNear) {
                allpixels[i] = Color.alpha(Color.TRANSPARENT);
            }
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

    public static Bitmap getStartBitmap(int eyesVisible, CellOrientation cellOrientation) {
        if (startHoleBitmap == null) {
            initStartBitmaps();
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

    public static Bitmap getObstacleBitmap() {
        if (stoneBitmap == null) {
            initObstaclesBitmaps();
        }

        return stoneBitmap;
    }

    public static Bitmap getBodyBitmap(CellOrientation cellOrientation, int frameNumber) {
        if (bodyBitmaps == null)
            initBodyBitmaps();

        return bodyBitmaps.get(cellOrientation)[frameNumber];
    }

    public static Bitmap getHeadBitmap(CellOrientation cellOrientation, int frameNumber) {
        if (headBitmaps == null) {
            initHeadBitmaps();
        }

        return headBitmaps.get(cellOrientation)[frameNumber];
    }

    public static Bitmap getTurnBitmap(CellOrientation bodyOrientation, CellOrientation headOrientation) {
        if (turnBitmap == null) {
            initTurnBitmaps();
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

    public static void initImages(Resources resources) {
        initSpriteSheets(resources);
        initObstaclesBitmaps();
        initStartBitmaps();
        initTurnBitmaps();
        initBodyBitmaps();
    }

    public static void initSpriteSheets(Resources resources) {
        if (spriteSheet1 == null) {
            spriteSheet1 = new SpriteSheet(resources, spriteSheet1Id, 4,3);
        }

        if (spriteSheet2 == null) {
            spriteSheet2 = new SpriteSheet(resources, spriteSheet2Id, 4, 7);
        }
    }

    public static void initTurnBitmaps() {
        if (turnBitmap == null) {
            turnBitmap = spriteSheet2.getBitmapFromPosition(2, 3);
            turnBitmap = rotateBitmap(turnBitmap, 90);
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

    public static void initStartBitmaps() {
        if (startHoleBitmap == null) {
            startHoleBitmap = spriteSheet2.getBitmapFromPosition(3, 2);
            startHoleBitmap = makeBitmapTransparent(startHoleBitmap, backgroundColor);
        }

        if (startHoleWithEyesBitmap == null) {
            startHoleWithEyesBitmap = spriteSheet2.getBitmapFromPosition(1, 3);
            startHoleWithEyesBitmap = makeBitmapTransparent(startHoleWithEyesBitmap, backgroundColor);
        }

        if (startDown == null) {
            startDown = spriteSheet1.getBitmapFromPosition(2, 2);
            startDown = makeBitmapTransparent(startDown, backgroundColor);
        }

        if (startUp == null) {
            startUp = spriteSheet1.getBitmapFromPosition(2, 1);
            startUp = makeBitmapTransparent(startUp, backgroundColor);
        }

        if (startLeft == null) {
            startLeft = spriteSheet2.getBitmapFromPosition(3, 3);
            startLeft = makeBitmapTransparent(startLeft, backgroundColor);
        }

        if (startRight == null) {
            startRight = spriteSheet2.getBitmapFromPosition(3, 1);
            startRight = makeBitmapTransparent(startRight, backgroundColor);
        }



    }

    public static void initObstaclesBitmaps() {
        if (stoneBitmap == null) {
            stoneBitmap = spriteSheet1.getBitmapFromPosition(4, 1);
            stoneBitmap = makeBitmapTransparent(stoneBitmap, backgroundColor);
        }
    }

    public static void initSnakeBitmaps() {
        initHeadBitmaps();
        initBodyBitmaps();
    }

    public static void initHeadBitmaps() {
        if (headBitmaps == null) {
            if (headBitmapsDown == null) {
                headBitmapsDown = new Bitmap[3];
                headBitmapsDown[0] = spriteSheet2.getBitmapFromPosition(1,2);
                headBitmapsDown[0] = makeBitmapTransparent(headBitmapsDown[0], backgroundColor);
                headBitmapsDown[1] = emptyTransparentBitmap;
                headBitmapsDown[2] = spriteSheet2.getBitmapFromPosition(1,7);
                headBitmapsDown[2] = makeBitmapTransparent(headBitmapsDown[2], backgroundColor);
            }

            if (headBitmapsUp == null) {
                headBitmapsUp = new Bitmap[3];
                headBitmapsUp[0] = rotateBitmap(headBitmapsDown[0], 180);
                headBitmapsUp[1] = rotateBitmap(headBitmapsDown[1], 180);
                headBitmapsUp[2] = rotateBitmap(headBitmapsDown[2], 180);
            }

            if (headBitmapsLeft == null) {
                headBitmapsLeft = new Bitmap[3];
                headBitmapsLeft[0] = rotateBitmap(headBitmapsDown[0], 90);
                headBitmapsLeft[1] = rotateBitmap(headBitmapsDown[1], 90);
                headBitmapsLeft[2] = rotateBitmap(headBitmapsDown[2], 90);

            }

            if (headBitmapsRight == null) {
                headBitmapsRight = new Bitmap[3];
                headBitmapsRight[0] = rotateBitmap(headBitmapsDown[0], 270);
                headBitmapsRight[1] = rotateBitmap(headBitmapsDown[1], 270);
                headBitmapsRight[2] = rotateBitmap(headBitmapsDown[2], 270);
            }

            headBitmaps = new HashMap<CellOrientation, Bitmap[]>();
            headBitmaps.put(CellOrientation.Down, headBitmapsDown);
            headBitmaps.put(CellOrientation.Up, headBitmapsUp);
            headBitmaps.put(CellOrientation.Left, headBitmapsLeft);
            headBitmaps.put(CellOrientation.Right, headBitmapsRight);
        }
    }

    public static void initBodyBitmaps() {
        if (bodyBitmaps == null) {
            if (bodyBitmapsDown == null) {
                bodyBitmapsDown = new Bitmap[4];
                bodyBitmapsDown[0] = spriteSheet1.getBitmapFromPosition(1, 1);
                bodyBitmapsDown[0] = DrawableController.rotateBitmap(bodyBitmapsDown[0], 180);
                bodyBitmapsDown[0] = makeBitmapTransparent(bodyBitmapsDown[0], backgroundColor);
                bodyBitmapsDown[1] = spriteSheet2.getBitmapFromPosition(4,1);
                bodyBitmapsDown[1] = makeBitmapTransparent(bodyBitmapsDown[1], backgroundColor);
                bodyBitmapsDown[2] = spriteSheet2.getBitmapFromPosition(4,2);
                bodyBitmapsDown[2] = makeBitmapTransparent(bodyBitmapsDown[2], backgroundColor);
                bodyBitmapsDown[3] = spriteSheet2.getBitmapFromPosition(4,3);
                bodyBitmapsDown[3] = makeBitmapTransparent(bodyBitmapsDown[3], backgroundColor);
            }

            if (bodyBitmapsUp == null) {
                bodyBitmapsUp = new Bitmap[4];
                bodyBitmapsUp[0] = rotateBitmap(bodyBitmapsDown[0], 180);
                bodyBitmapsUp[1] = rotateBitmap(bodyBitmapsDown[1], 180);
                bodyBitmapsUp[2] = rotateBitmap(bodyBitmapsDown[2], 180);
                bodyBitmapsUp[3] = rotateBitmap(bodyBitmapsDown[3], 180);
            }

            if (bodyBitmapsLeft == null) {
                bodyBitmapsLeft = new Bitmap[4];
                bodyBitmapsLeft[0] = rotateBitmap(bodyBitmapsDown[0], 90);
                bodyBitmapsLeft[1] = rotateBitmap(bodyBitmapsDown[1], 90);
                bodyBitmapsLeft[2] = rotateBitmap(bodyBitmapsDown[2], 90);
                bodyBitmapsLeft[3] = rotateBitmap(bodyBitmapsDown[3], 90);
            }

            if (bodyBitmapsRight == null) {
                bodyBitmapsRight = new Bitmap[4];
                bodyBitmapsRight[0] = rotateBitmap(bodyBitmapsDown[0], 270);
                bodyBitmapsRight[1] = rotateBitmap(bodyBitmapsDown[1], 270);
                bodyBitmapsRight[2] = rotateBitmap(bodyBitmapsDown[2], 270);
                bodyBitmapsRight[3] = rotateBitmap(bodyBitmapsDown[3], 270);
            }

            bodyBitmaps = new HashMap<CellOrientation, Bitmap[]>();
            bodyBitmaps.put(CellOrientation.Down, bodyBitmapsDown);
            bodyBitmaps.put(CellOrientation.Up, bodyBitmapsUp);
            bodyBitmaps.put(CellOrientation.Left, bodyBitmapsLeft);
            bodyBitmaps.put(CellOrientation.Right, bodyBitmapsRight);
        }

    }

}
