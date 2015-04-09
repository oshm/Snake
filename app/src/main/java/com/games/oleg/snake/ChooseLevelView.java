package com.games.oleg.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by oleg.shlemin on 24.03.2015.
 */
public class ChooseLevelView extends View {
    private final ChooseLevelActivity chooseLevelActivity;
    private float width;            //width of one square
    private float height;           //height of one square
    int maxCellsX = 5;
    int maxCellsY = 4;

    public ChooseLevelView(Context context) {
        super(context);
        this.chooseLevelActivity = (ChooseLevelActivity)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        this.width = (right - left)/(float) maxCellsX;;
        this.height = (bottom - top)/(float) maxCellsY;
    }

    protected void onDraw(Canvas canvas) {
        //Draw background
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.game_background));
        canvas.drawRect(0,0, getWidth(), getHeight(), background);

        Paint foreground = new Paint();
        foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
        foreground.setStyle(Paint.Style.FILL);
        foreground.setTextSize(height*0.75f);
        foreground.setTextScaleX(width/height);
        foreground.setTextAlign(Paint.Align.CENTER);

        //Draw the number
        Paint.FontMetrics fm = foreground.getFontMetrics();
        float x = width / 2;
        // Centering in Y: measure ascent/descent first
        float y = height / 2 - (fm.ascent + fm.descent) / 2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                canvas.drawText(getTileString(i, j), i
                        * width + x, j * height + y, foreground);
            }
        }
    }

    private String getTileString(int x, int y) {
        return Integer.toString(x + y*5);
    }


}
