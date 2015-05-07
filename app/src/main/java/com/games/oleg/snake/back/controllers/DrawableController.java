package com.games.oleg.snake.back.controllers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

import com.games.oleg.snake.R;
import com.games.oleg.snake.back.models.SpriteSheet;
import com.games.oleg.snake.back.models.cells.CellOrientation;

import java.util.Dictionary;
import java.util.Enumeration;
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

    private static Bitmap headBitmap;
    private static Bitmap headBitmapUp;
    private static Bitmap headBitmapDown;
    private static Bitmap headBitmapLeft;
    private static Bitmap headBitmapRight;

    private static Bitmap bodyBitmap;
    private static Bitmap bodyBitmapUp;
    private static Bitmap bodyBitmapDown;
    private static Bitmap bodyBitmapLeft;
    private static Bitmap bodyBitmapRight;

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

    private static HashMap<CellOrientation, Bitmap[]> bodyAnimationBitmaps;
    private static Bitmap[] bodyAnimationsDown;
    private static Bitmap[] bodyAnimationsUp;
    private static Bitmap[] bodyAnimationsLeft;
    private static Bitmap[] bodyAnimationsRight;



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

    public static Bitmap getObstacleBitmap() {
        if (stoneBitmap == null) {
            initObstaclesBitmaps();
        }

        return stoneBitmap;
    }

    public static Bitmap getBodyBitmap(CellOrientation cellOrientation, Resources resources) {
        if (bodyBitmap == null) {
            initSnakeBitmaps();
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
            initSnakeBitmaps();
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

    public static void initImages(Resources resources) {
        initSpriteSheets(resources);
        initObstaclesBitmaps();
        initStartBitmaps(resources);
        initTurnBitmaps(resources);
    }

    public static void initSpriteSheets(Resources resources) {
        if (spriteSheet1 == null) {
            spriteSheet1 = new SpriteSheet(resources, spriteSheet1Id, 4,3);
        }

        if (spriteSheet2 == null) {
            spriteSheet2 = new SpriteSheet(resources, spriteSheet2Id, 4, 7);
        }
    }

    public static void initTurnBitmaps(Resources resources) {
        if (turnBitmap == null) {
            turnBitmap = spriteSheet2.getBitmapFromPosition(2, 3);
            turnBitmap = rotateBitmap(turnBitmap, 90);

            //turnBitmap = BitmapFactory.decodeResource(resources, turnId);
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
            startHoleBitmap = spriteSheet2.getBitmapFromPosition(3, 2);
            //startHoleBitmap = BitmapFactory.decodeResource(resources, startHoleId);
            startHoleBitmap = makeBitmapTransparent(startHoleBitmap, backgroundColor);
        }

        if (startHoleWithEyesBitmap == null) {
            startHoleWithEyesBitmap = spriteSheet2.getBitmapFromPosition(1, 3);
            //startHoleWithEyesBitmap = BitmapFactory.decodeResource(resources, startHoleWithEyesId);
            startHoleWithEyesBitmap = makeBitmapTransparent(startHoleWithEyesBitmap, backgroundColor);
        }

        if (startDown == null) {
            startDown = spriteSheet1.getBitmapFromPosition(2, 2);

            //startDown = BitmapFactory.decodeResource(resources, startDownId);
            startDown = makeBitmapTransparent(startDown, backgroundColor);
        }

        if (startUp == null) {
            startUp = spriteSheet1.getBitmapFromPosition(2, 1);
            startUp = makeBitmapTransparent(startUp, backgroundColor);
            //startUp = rotateBitmap(startDown, 180);
        }

        if (startLeft == null) {
            startLeft = spriteSheet2.getBitmapFromPosition(3, 3);
            startLeft = makeBitmapTransparent(startLeft, backgroundColor);
            //startLeft = rotateBitmap(startDown, 90);
        }

        if (startRight == null) {
            startRight = spriteSheet2.getBitmapFromPosition(3, 1);
            startRight = makeBitmapTransparent(startRight, backgroundColor);
            //startRight = rotateBitmap(startDown, 270);
        }



    }

    public static void initObstaclesBitmaps() {
        if (stoneBitmap == null) {
            stoneBitmap = spriteSheet1.getBitmapFromPosition(4, 1);
            stoneBitmap = makeBitmapTransparent(stoneBitmap, backgroundColor);
        }
    }

    public static void initSnakeBitmaps() {
        if (headBitmap == null) {
            headBitmap = spriteSheet1.getBitmapFromPosition(1, 2);
            headBitmap = DrawableController.rotateBitmap(headBitmap, 180);
            headBitmap = makeBitmapTransparent(headBitmap, backgroundColor);
        }

        if (bodyBitmap == null) {
            bodyBitmap = spriteSheet1.getBitmapFromPosition(1, 1);
            bodyBitmap = DrawableController.rotateBitmap(bodyBitmap, 180);
            bodyBitmap = makeBitmapTransparent(bodyBitmap, backgroundColor);
        }
    }

    public static void initSnakeMoveBitmaps() {
        if (bodyAnimationBitmaps == null) {
            if (bodyAnimationsDown == null) {
                bodyAnimationsDown = new Bitmap[3];
                bodyAnimationsDown[0] = spriteSheet2.getBitmapFromPosition(4,1);
                bodyAnimationsDown[0] = makeBitmapTransparent(bodyAnimationsDown[0], backgroundColor);
                bodyAnimationsDown[1] = spriteSheet2.getBitmapFromPosition(4,2);
                bodyAnimationsDown[1] = makeBitmapTransparent(bodyAnimationsDown[1], backgroundColor);
                bodyAnimationsDown[2] = spriteSheet2.getBitmapFromPosition(4,3);
                bodyAnimationsDown[2] = makeBitmapTransparent(bodyAnimationsDown[2], backgroundColor);
            }

            if (bodyAnimationsUp == null) {
                bodyAnimationsUp = new Bitmap[3];
                bodyAnimationsUp[0] = rotateBitmap(bodyAnimationsDown[0], 180);
                bodyAnimationsUp[1] = rotateBitmap(bodyAnimationsDown[0], 180);
                bodyAnimationsUp[2] = rotateBitmap(bodyAnimationsDown[0], 180);
            }

            if (bodyAnimationsLeft == null) {
                bodyAnimationsLeft = new Bitmap[3];
                bodyAnimationsLeft[0] = rotateBitmap(bodyAnimationsDown[0], 270);
                bodyAnimationsLeft[1] = rotateBitmap(bodyAnimationsDown[0], 270);
                bodyAnimationsLeft[2] = rotateBitmap(bodyAnimationsDown[0], 270);
            }

            if (bodyAnimationsRight == null) {
                bodyAnimationsRight = new Bitmap[3];
                bodyAnimationsRight[0] = rotateBitmap(bodyAnimationsDown[0], 90);
                bodyAnimationsRight[1] = rotateBitmap(bodyAnimationsDown[0], 90);
                bodyAnimationsRight[2] = rotateBitmap(bodyAnimationsDown[0], 90);
            }

            bodyAnimationBitmaps = new HashMap<CellOrientation, Bitmap[]>();
            bodyAnimationBitmaps.put(CellOrientation.Down, bodyAnimationsDown);
            bodyAnimationBitmaps.put(CellOrientation.Up, bodyAnimationsUp);
            bodyAnimationBitmaps.put(CellOrientation.Left, bodyAnimationsLeft);
            bodyAnimationBitmaps.put(CellOrientation.Right, bodyAnimationsRight);
        }

    }

}
