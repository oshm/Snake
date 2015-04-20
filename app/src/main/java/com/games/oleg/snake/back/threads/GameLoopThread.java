package com.games.oleg.snake.back.threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.games.oleg.snake.GameView;
import com.games.oleg.snake.NewGameView;

/**
 * Created by oleg.shlemin on 20.04.2015.
 */
public class GameLoopThread extends Thread{
    private NewGameView gameView;
    private boolean running = false;

    public GameLoopThread(NewGameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
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
        }
    }
}
