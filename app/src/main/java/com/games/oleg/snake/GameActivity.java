package com.games.oleg.snake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.controllers.GameController;
import com.games.oleg.snake.back.models.Position;


public class GameActivity extends Activity {
    private GameController gameController;
    private GameView gameView;
    public static final String KEY_LEVEL_NUMBER =
            "com.games.oleg.snake.level_number" ;
    private int levelNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_game);
        XmlResourceParser level;
        gameController = new GameController();
        levelNumber = getIntent().getIntExtra(KEY_LEVEL_NUMBER, 1);
        level = readLevel(levelNumber);
        gameController.startGame(level, getApplicationContext());

        gameView = new GameView(this, gameController.getField());
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(gameView);
    }

    private  XmlResourceParser readLevel (int levelNumber) {
        XmlResourceParser level;
        String levelName = "level" + Integer.toString(levelNumber+1);
        int levelId = getResources().getIdentifier(levelName, "xml", getPackageName());
        level = this.getResources().getXml(levelId);
        return level;
    }

    public Field getField() {
        return gameController.getField();
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

        Boolean isMovedToNew = gameController.moveToPosition(this.getApplicationContext(),
                touchedCellPosition);
        Boolean isMovedBack = gameController.moveBackIfBody(getApplicationContext(), touchedCellPosition);

        Boolean needUpdate = isMovedToNew || isMovedBack;

        if (needUpdate)
            gameView.updateField(gameController.getField());

        if (gameController.isLevelFinished()) {
            Dialog levelFinishedDialog = new LevelFinishedDialog(this, levelNumber);
            levelFinishedDialog.show();
        }
        return false;
    }

    private Position findTouchedCellPosition(int touchX, int touchY) {

        int cellX = ((int) Math.ceil(touchX/gameView.getCellWidth())) -1 ;
        int cellY = ((int) Math.ceil(touchY/gameView.getCellHeight())) -1;

        Position touchedCellPosition = new Position(cellX, cellY);

        //for testing
        gameView.updateTouched(touchX, touchY);
        //
        return touchedCellPosition;
    }
}
