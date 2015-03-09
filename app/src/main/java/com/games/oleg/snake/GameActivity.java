package com.games.oleg.snake;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.controllers.GameController;


public class GameActivity extends Activity {
    private GameController gameController;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_game);
        XmlResourceParser level = this.getResources().getXml(R.xml.level1);
        gameController.startGame(level);

        gameController = new GameController();
        gameView = new GameView(this, gameController.getField());
        setContentView(gameView);

        gameController.moveDown();
        gameController.moveDown();
        gameController.moveRight();
        gameController.moveRight();
        Field field = gameController.getField();
        String boo = "";
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
}
