package com.games.oleg.snake.back.threads;

import android.graphics.Canvas;
import com.games.oleg.snake.NewGameView;
import com.games.oleg.snake.back.controllers.GameController;

/**
 * Created by oleg.shlemin on 20.04.2015.
 */
public class GameLoopThread extends Thread{
    private NewGameView gameView;
    private boolean running = false;
    static final long FPS = 10;
    long threadStartTime;
    StartCellAnimationThread startCellAnimationThread;
    private boolean isStartAnimationNeeded = true;

    public GameLoopThread(NewGameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        threadStartTime = System.currentTimeMillis();

        while (running) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            long threadWorkTime = System.currentTimeMillis() - threadStartTime;
            if (threadWorkTime > 100000) {
                threadWorkTime = 0;
                threadStartTime = startTime;
            }

            try {
                canvas = gameView.getHolder().lockCanvas();
                if (canvas != null) {
                    animateStartCell(threadWorkTime);
                    synchronized (gameView.getHolder()) {
                        gameView.drawGame(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }

            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }

    public void setStartAnimation(boolean isNeeded) {
        this.isStartAnimationNeeded = isNeeded;
        if (!isNeeded && this.startCellAnimationThread!=null)
            this.startCellAnimationThread.setRunning(false);
    }

    private boolean isStartCellAnimate(long threadWorkTime) {
        boolean isStartTimePassed = threadWorkTime > 5000;
        boolean isThreadFree = (startCellAnimationThread == null) ||
                (!startCellAnimationThread.isAlive());

        return (isStartAnimationNeeded && isStartTimePassed && isThreadFree);
    }


    private void animateStartCell(long threadWorkTime) {
        if (isStartCellAnimate(threadWorkTime)) {
            startCellAnimationThread = new StartCellAnimationThread();
            startCellAnimationThread.setRunning(true);
            startCellAnimationThread.start();
        }
    }
}
