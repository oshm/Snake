package com.games.oleg.snake.back.threads;

import android.util.Log;

import com.games.oleg.snake.GameView;
import com.games.oleg.snake.NewGameView;

/**
 * Created by oleg.shlemin on 15.04.2015.
 */
public class StartCellAnimationThread extends Thread {
    private final static String TAG = "StartCellAnimation";
    public final static int MAX_FPS = 50;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    private boolean running = false;
    NewGameView gameView;
    long[] stateTransitionTimes =  new long[]{2000,5000,5500,6000, 6500};
    long threadWorkTime = 0;

    public StartCellAnimationThread(NewGameView gameView) {
        this.gameView = gameView;
    }

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
        while ( running &&
                (threadWorkTime < (stateTransitionTimes[stateTransitionTimes.length-1] + 1000)) ) {
            beginTime = System.currentTimeMillis();
            threadWorkTime = System.currentTimeMillis() - threadStartTime;

            // update game state
            boolean isStateEyes = (threadWorkTime > stateTransitionTimes[0])
                    && (threadWorkTime < stateTransitionTimes[1]);
            isStateEyes = isStateEyes | ((threadWorkTime > stateTransitionTimes[2])
                    && (threadWorkTime < stateTransitionTimes[3]));
            isStateEyes = isStateEyes | (threadWorkTime > stateTransitionTimes[4]);

            if (isStateEyes)
                gameView.updateStart(1);
            else
                gameView.updateStart(0);

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
