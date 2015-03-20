package com.games.oleg.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellType;

/**
 * Created by oleg on 09.03.15.
 */
public class GameView extends View {
    private final GameActivity gameActivity;
    private Cell[][] gridToDraw;
    private int maxCellsX;        // number of cells in field horizontally
    private int maxCellsY;        //number of cells in field vertically

    private float width;            //width of one square
    private float height;           //height of one square
    private final Rect selRect = new Rect();
    float x = -30;
    float y = -30;

    public GameView(Context context, Field fieldToDraw) {
        super(context);
        this.gameActivity = (GameActivity) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.gridToDraw = fieldToDraw.getGrid();
        maxCellsX = fieldToDraw.getSizeX();
        maxCellsY = fieldToDraw.getSizeY();
    }

    // commented because gets whole size, not only view size
/*
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w/(float) maxCellsX;
        height = h/(float)maxCellsY;
        super.onSizeChanged(w,h,oldw, oldh);
    }
*/

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        this.width = (right - left)/(float) maxCellsX;;
        this.height = (bottom - top)/(float) maxCellsY;;
    }
/*
    @Override
    protected void onLayoutChange(int left, int top, int right, int bottom,
                                  int oldLeft, int oldTop, int oldRight, int oldBottom) {
        this.width = right - left;
        this.height = bottom - top;

    }
*/







    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawGrid(canvas);
        drawField(canvas);

        // for testing
        Paint cellColour = new Paint();
        cellColour.setColor(getResources().getColor(R.color.snake_body));
        canvas.drawCircle(x, y, 30, cellColour);
        //
    }

    public void updateTouched(float x, float y) {
        this.x = x;
        this.y = y;
        this.invalidate();
    }

    private void drawBackground(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.game_background));
        canvas.drawRect(0,0, getWidth(), getHeight(), background);
    }

    private void drawGrid(Canvas canvas) {
        Paint gridColour = new Paint();
        gridColour.setColor(getResources().getColor(R.color.grid_line));

        for (int i = 0; i < maxCellsY; i++ ) {
            canvas.drawLine(0, i*height, getWidth(), i*height,
                    gridColour);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height
                    + 1, gridColour);
        }

        for (int i = 0; i < maxCellsX; i++ ) {
            canvas.drawLine(i * width, 0, i * width, getHeight(),
                    gridColour);
            canvas.drawLine(i * width + 1, 0, i * width + 1,
                    getHeight(), gridColour);
        }
    }

    private void drawField(Canvas canvas) {
        for (int currentY = 0; currentY < maxCellsY; currentY++) {
            for (int currentX = 0; currentX < maxCellsX; currentX++) {
                drawCell(canvas, currentX, currentY);
            }
        }


    }

    private void drawCell(Canvas canvas, int currentX, int currentY) {

        Paint cellColour = new Paint();

        Cell cellToDraw = gridToDraw[currentY][currentX];
        Boolean boo = cellToDraw.getCellType() == CellType.HeadCell;
        switch (cellToDraw.getCellType()) {
            case HeadCell: {
                cellColour.setColor(getResources().getColor(R.color.snake_head));
                canvas.drawCircle(currentX * width + width / 2, currentY * height + height / 2,
                        width / 2, cellColour);
                break;
            }
            case BodyCell: {
                cellColour.setColor(getResources().getColor(R.color.snake_body));
                canvas.drawCircle(currentX * width + width / 2, currentY * height + height / 2,
                        width / 2, cellColour);
                break;
            }
            case ObstacleCell: {
                cellColour.setColor(getResources().getColor(R.color.obstacle));
                canvas.drawCircle(currentX * width + width / 2, currentY * height + height / 2,
                        width / 2, cellColour);
                break;
            }
        }


    }

    public void updateField(Field updatedField) {
        this.gridToDraw = updatedField.getGrid();
        this.maxCellsX = updatedField.getSizeX();
        this.maxCellsY = updatedField.getSizeY();
        this.invalidate();
    }

    public float getCellWidth() {
        return this.width;
    }

    public float getCellHeight() {
        return this.height;
    }
}