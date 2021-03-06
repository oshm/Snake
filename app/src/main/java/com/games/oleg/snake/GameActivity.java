package com.games.oleg.snake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;

import com.games.oleg.snake.back.controllers.GameController;
import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.threads.MoveSnakeAnimationThread;


public class GameActivity extends Activity {
    private GameController gameController;
    private NewGameView gameView;
    public static final String KEY_LEVEL_NUMBER =
            "com.games.oleg.snake.level_number" ;
    private int levelNumber;
    /*
    private Handler animationHandler = new Handler();
    private Runnable  animationRunnable= new Runnable() {
        @Override
        public void run() {
            try {
                gameView.updateStart(0);
                Thread.sleep(1000);
                gameView.updateStart(1);
                Thread.sleep(1000);
                gameView.updateStart(0);
                Thread.sleep(1000);
                gameView.updateStart(1);
                Thread.sleep(1000);
                gameView.updateStart(0);
                //Thread.sleep(1000);
                //gameView.updateStart(1);
            }
            catch (InterruptedException ie) {}
            animationHandler.postDelayed(this, 5000);
        }

    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_game);
        XmlResourceParser level;
        gameController = GameController.getInstance();
        levelNumber = getIntent().getIntExtra(KEY_LEVEL_NUMBER, 1);
        level = readLevel(levelNumber);
        gameController.startGame(level, getApplicationContext());

        gameView = new NewGameView(this, gameController.getField());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(gameView);
       // animationHandler.postDelayed(animationRunnable, 5000);

    }

    private  XmlResourceParser readLevel (int levelNumber) {
        XmlResourceParser level;
        String levelName = "level" + Integer.toString(levelNumber+1);
        int levelId = getResources().getIdentifier(levelName, "xml", getPackageName());
        level = this.getResources().getXml(levelId);
        return level;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);

        // Getting touched cell position
        int[] location = new int[2];
        this.gameView.getLocationOnScreen(location);
        float screenX = event.getRawX();
        float screenY = event.getRawY();
        float viewX = screenX - location[0];
        float viewY = screenY - location[1];
        Position touchedCellPosition = findTouchedCellPosition((int) viewX, (int) viewY);

        if (gameController.isLevelFinished(touchedCellPosition)) {
            Dialog levelFinishedDialog = new LevelFinishedDialog(this, levelNumber);
            levelFinishedDialog.show();
        }
        else if (gameController.isOnFinish(touchedCellPosition))
            return false;

        Boolean isMovedToNew = gameController.moveToPosition(this.getApplicationContext(),
                touchedCellPosition);
        Boolean isMovedBack = gameController.moveBackIfBody(getApplicationContext(), touchedCellPosition);
        gameView.getGameLoop().setStartAnimation(this.gameController.isOnStart());
        Boolean needUpdate = isMovedToNew || isMovedBack;

        if (needUpdate) {
            gameController.computeCurrentStartOrientation();
            gameView.updateField(gameController.getField());
        }

        if (isMovedToNew) {
            MoveSnakeAnimationThread moveSnakeAnimationThread = new MoveSnakeAnimationThread();
            moveSnakeAnimationThread.setRunning(true);
            moveSnakeAnimationThread.start();
        }

        return false;
    }

    private Position findTouchedCellPosition(int touchX, int touchY) {

        int cellX = ((int) Math.ceil(touchX/gameView.getCellWidth())) -1 ;
        int cellY = ((int) Math.ceil(touchY/gameView.getCellHeight())) -1;

        Position touchedCellPosition = new Position(cellX, cellY);

        //for testing
        //gameView.updateTouched(touchX, touchY);
        //
        return touchedCellPosition;
    }

    public void setDrawThreadRunning(boolean run) {
        gameView.setDrawThreadRunning(run);
    }

}
