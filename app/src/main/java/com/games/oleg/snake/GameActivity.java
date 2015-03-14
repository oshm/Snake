package com.games.oleg.snake;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.controllers.GameController;
import com.games.oleg.snake.back.models.Position;


public class GameActivity extends Activity {
    private GameController gameController;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_game);
        XmlResourceParser level = this.getResources().getXml(R.xml.level1);
        gameController = new GameController();
        gameController.startGame(level);

        gameView = new GameView(this, gameController.getField());
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(gameView);

        //gameController.moveDown();
        //gameController.moveRight();
        //gameController.moveRight();
        //Field field = gameController.getField();
        //String boo = "";
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

        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        Position touchedCellPosition = findTouchedCellPosition(touchX, touchY);

        Boolean needUpdate = gameController.moveToPosition(touchedCellPosition);
        if (needUpdate)
            gameView.updateField(gameController.getField());


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
