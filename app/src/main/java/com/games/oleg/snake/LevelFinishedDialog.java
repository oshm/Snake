package com.games.oleg.snake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by oleg.shlemin on 25.03.2015.
 */
public class LevelFinishedDialog extends Dialog implements View.OnClickListener {
    private int levelNumber;
    Button nextLevelButton;
    Button levelMenuButton;
    public static final String KEY_LEVEL_NUMBER =
            "com.games.oleg.snake.level_number" ;
    private Activity gameActivity;


    public LevelFinishedDialog(GameActivity activity, int levelNumber) {
        super(activity);
        this.setCanceledOnTouchOutside(false);
        this.levelNumber = levelNumber;
        gameActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level_finished);
        nextLevelButton = (Button) findViewById(R.id.next_lvl_button);
        nextLevelButton.setOnClickListener(this);
        levelMenuButton = (Button) findViewById(R.id.level_menu_button);
        levelMenuButton.setOnClickListener(this);
    }

    public void goToNextLvl(Context context) {
        //gameActivity.startNewLevel(levelNumber);
        Intent i = new Intent(context, GameActivity.class);
        i.putExtra(KEY_LEVEL_NUMBER, levelNumber+1);
        context.startActivity(i);
    }

    public void goToLevelMenu(Context context) {
        Intent i = new Intent(context, ChooseLevelActivity.class);
        context.startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (view == nextLevelButton) {
            goToNextLvl(gameActivity);
            this.closeOptionsMenu();
        }
        else if (view == levelMenuButton) {
            goToLevelMenu(gameActivity);
            this.closeOptionsMenu();
        }
    }
}
