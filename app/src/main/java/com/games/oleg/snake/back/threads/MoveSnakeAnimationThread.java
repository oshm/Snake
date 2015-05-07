package com.games.oleg.snake.back.threads;


import android.util.Log;

import com.games.oleg.snake.back.controllers.GameController;

/**
 * Created by oleg.shlemin on 07.05.2015.
 */
public class MoveSnakeAnimationThread extends Thread {
    private final static String TAG = "StartCellAnimation";
    public final static int MAX_FPS = 50;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    private boolean running = false;
    long threadWorkTime = 0;

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        Log.d(TAG, "Starting start cell animation");
        long threadStartTime; // the time when the thread begun
        long beginTime;     // the time when the cycle begun
        long timeDiff;      // the time it took for the cycle to execute
        int sleepTime;      // ms to sleep (<0 if we're behind)
        sleepTime = 0;

        threadStartTime = System.currentTimeMillis();
        while (running) {
            beginTime = System.currentTimeMillis();
            threadWorkTime = System.currentTimeMillis() - threadStartTime;


            // update game state







            // calculate how long did the cycle take
            timeDiff = System.currentTimeMillis() - beginTime;
            // calculate sleep time
            sleepTime = (int) (FRAME_PERIOD - timeDiff);

            if (sleepTime > 0) {
                // if sleepTime > 0 we're OK
                try {
                    // send the thread to sleep for a short period
                    // very useful for battery saving
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }
        }
        this.setRunning(false);
    }

}