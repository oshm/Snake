package com.games.oleg.snake.back.threads;

import android.graphics.Canvas;
import com.games.oleg.snake.NewGameView;

/**
 * Created by oleg.shlemin on 20.04.2015.
 */
public class GameLoopThread extends Thread{
    private NewGameView gameView;
    private boolean running = false;
    static final long FPS = 10;
    long threadStartTime;
    StartCellAnimationThread startCellAnimationThread;

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

            animateStartCell(threadWorkTime);

            try {
                canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                gameView.drawGame(canvas);
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


    private void isStartCellAnimate() {
        
    }


    private void animateStartCell(long threadWorkTime) {
        if ( (threadWorkTime > 5000) ) {
            if (( startCellAnimationThread == null) || (!startCellAnimationThread.isAlive())) {
                startCellAnimationThread = new StartCellAnimationThread(gameView);
                startCellAnimationThread.setRunning(true);
                startCellAnimationThread.start();
            }
        }
    }
}
