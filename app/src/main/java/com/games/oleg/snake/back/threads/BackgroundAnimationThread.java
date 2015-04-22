package com.games.oleg.snake.back.threads;

import android.util.Log;

import com.games.oleg.snake.GameView;
import com.games.oleg.snake.NewGameView;

/**
 * Created by oleg.shlemin on 15.04.2015.
 */
public class BackgroundAnimationThread extends Thread {
    private final static String TAG = "BckgrndAnimationThread";
    public final static int MAX_FPS = 50;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    private boolean running = false;
    NewGameView gameView;
    long threadStartTime; // the time when the thread begun
    long threadWorkTime = 0;
    long startAnimationTriggerTime = 0;


    public BackgroundAnimationThread(NewGameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        Log.d(TAG, "Starting background animation loop");

        long beginTime;     // the time when the cycle begun
        long timeDiff;      // the time it took for the cycle to execute
        int sleepTime;      // ms to sleep (<0 if we're behind)
        int framesSkipped;  // number of frames being skipped
        sleepTime = 0;

        threadStartTime = System.currentTimeMillis();
        startAnimationTriggerTime = threadStartTime;
        while (running) {
            beginTime = System.currentTimeMillis();
            threadWorkTime = System.currentTimeMillis() - threadStartTime;
            if (threadWorkTime > 100000)
                threadWorkTime = 0;
            framesSkipped = 0;  // resetting the frames skipped
            // update game state
            if ( (threadWorkTime - (threadStartTime - startAnimationTriggerTime)) > 10000) {
                StartCellAnimationThread startCellAnimationThread =
                        new StartCellAnimationThread(gameView);
                startCellAnimationThread.setRunning(true);
                startCellAnimationThread.start();
                startAnimationTriggerTime = System.currentTimeMillis();
            }


            // calculate how long did the cycle take
            timeDiff = System.currentTimeMillis() - beginTime;
            // calculate sleep time
            sleepTime = (int)(FRAME_PERIOD - timeDiff);

            if (sleepTime > 0) {
                // if sleepTime > 0 we're OK
                try {
                    // send the thread to sleep for a short period
                    // very useful for battery saving
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {}
            }
        }

    }
}
