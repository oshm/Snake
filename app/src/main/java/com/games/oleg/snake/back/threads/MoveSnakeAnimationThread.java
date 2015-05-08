package com.games.oleg.snake.back.threads;


import android.util.Log;

import com.games.oleg.snake.back.controllers.GameController;

/**
 * Created by oleg.shlemin on 07.05.2015.
 */
public class MoveSnakeAnimationThread extends GameThread {
    protected final String TAG = "MoveSnakeAnimation";
    private final int animationInterval = 50;

    @Override
    protected void threadAction() {
        // Calculating frame number
        int x = ((int) (threadWorkTime/animationInterval));
        int frameNumber = ((int) (threadWorkTime/animationInterval)) + 1;
        if (frameNumber > 3) {
            frameNumber = 0;
        }
        if (frameNumber < 0) {
            frameNumber = 0;
        }

        // Setting body frame
        GameController.getInstance().getField().getCell(
                GameController.getInstance().getSnake().getLastBodyPosition()).
                setFrameNumber(frameNumber);


        // Setting head frame
        if (frameNumber == 0) {
            GameController.getInstance().getField().getCell(
                    GameController.getInstance().getSnake().getHeadPosition()).
                    setFrameNumber(frameNumber);
        }
        else if (frameNumber==3) {
            GameController.getInstance().getField().getCell(
                    GameController.getInstance().getSnake().getHeadPosition()).
                    setFrameNumber(2);
        }
        else {
            GameController.getInstance().getField().getCell(
                    GameController.getInstance().getSnake().getHeadPosition()).
                    setFrameNumber(1);
        }

    }

    @Override
    protected boolean threadCondition() {
        return (running && threadWorkTime < animationInterval*4);
    }
}
