package com.games.oleg.snake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by oleg.shlemin on 25.03.2015.
 */
public class LevelFinishedDialog extends Dialog implements View.OnClickListener {
    private int levelNumber;
    Button nextLevelButton;
    Button mainMenuButton;
    public static final String KEY_LEVEL_NUMBER =
            "com.games.oleg.snake.level_number" ;
    private Activity gameActivity;

    public LevelFinishedDialog(Activity activity, int levelNumber) {
        super(activity);
        this.setCanceledOnTouchOutside(false);
        this.levelNumber = levelNumber;
        gameActivity = activity;
        nextLevelButton = (Button) findViewById(R.id.next_lvl_button);
        nextLevelButton.setOnClickListener(this);
        /*
        nextLevelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                goToNextLvl(activity);
            }
        });

        mainMenuButton = (Button) findViewById(R.id.main_menu_button);
        mainMenuButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                goToMainMenu();
            }
        });
        */
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_level_finished);
    }

    public void goToNextLvl(Context context) {
        Intent i = new Intent(context, GameActivity.class);
        i.putExtra(KEY_LEVEL_NUMBER, levelNumber);
        context.startActivity(i);
    }

    public void goToMainMenu() {

    }


    @Override
    public void onClick(View view) {
        if (view == nextLevelButton) {
           goToNextLvl(gameActivity);
        }
    }
}
