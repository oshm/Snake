package com.games.oleg.snake;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by oleg.shlemin on 25.03.2015.
 */
public class LevelFinishedDialog extends Dialog {

    public LevelFinishedDialog(Context context) {
        super(context);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_level_finished);
    }

}
