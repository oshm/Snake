package com.games.oleg.snake.back.threads;

import com.games.oleg.snake.back.controllers.GameController;

/**
 * Created by oleg on 18.05.15.
 */
public class MoveSnakeBackAnimationThread extends GameThread {
    protected final String TAG = "MoveSnakeAnimation";
    private final int ANIMATION_INTERVAL = 50;
    private final int MAX_FRAME_NUMBER = 3;

    @Override
    protected void threadAction() {
        // Calculating frame number
        int frameNumber = ((int) (threadWorkTime/ ANIMATION_INTERVAL)) + 1;
        if (frameNumber > MAX_FRAME_NUMBER) {
            frameNumber = 0;
        }
        if (frameNumber < 0) {
            frameNumber = 0;
        }

        // Setting body frame
        GameController.getInstance().getField().getCell(
                GameController.getInstance().getSnake().getLastBodyPosition()).
                setFrameNumber(frameNumber);

        GameController.getInstance().getField().getCell(
                GameController.getInstance().getSnake().getHeadPosition()).
                setFrameNumber(frameNumber);
    }

    @Override
    protected boolean threadCondition() {
        return (running && threadWorkTime < ANIMATION_INTERVAL*4);
    }
}
