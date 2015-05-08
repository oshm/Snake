package com.games.oleg.snake.back.threads;

import android.util.Log;

import com.games.oleg.snake.NewGameView;
import com.games.oleg.snake.back.controllers.GameController;

/**
 * Created by oleg.shlemin on 15.04.2015.
 */
public class StartCellAnimationThread extends GameThread {
    protected final String TAG = "StartCellAnimation";
    long[] stateTransitionTimes =  new long[]{1000, 2000, 2100, 2200, 2300};


    @Override
    protected void threadAction() {
        boolean isStateEyes = (threadWorkTime > stateTransitionTimes[0])
                && (threadWorkTime < stateTransitionTimes[1]);
        isStateEyes = isStateEyes | ((threadWorkTime > stateTransitionTimes[2])
                && (threadWorkTime < stateTransitionTimes[3]));
        isStateEyes = isStateEyes | (threadWorkTime > stateTransitionTimes[4]);

        if (isStateEyes)
            GameController.getInstance().getField().updateStartEyes(1);
        else
            GameController.getInstance().getField().updateStartEyes(0);
    }

    @Override
    protected boolean threadCondition() {
        return ( running &&
                (threadWorkTime < (stateTransitionTimes[stateTransitionTimes.length-1] + 100)) );
    }
}
