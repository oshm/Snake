package com.games.oleg.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainMenuActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        View startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(this);

        View exitGameButton = findViewById(R.id.exit_game_button);
        exitGameButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_game_button: {
                //startGame();
                goChoseLevel();
                break;
            }
        }
    }

    public void startGame() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void goChoseLevel() {
        Intent i = new Intent(this, ChooseLevelActivity.class);
        startActivity(i);
    }


}
