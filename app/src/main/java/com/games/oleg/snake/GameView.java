package com.games.oleg.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellType;

/**
 * Created by oleg on 09.03.15.
 */
public class GameView extends View {
    private final GameActivity gameActivity;
    private Cell[][] gridToDraw;
    private Position startPosition;
    private Position finishPosition;
    private int maxCellsX;        // number of cells in field horizontally
    private int maxCellsY;        //number of cells in field vertically

    private float width;            //width of one square
    private float height;           //height of one square
    float x = -30;
    float y = -30;

    public GameView(Context context, Field fieldToDraw) {
        super(context);
        this.gameActivity = (GameActivity) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.gridToDraw = fieldToDraw.getGrid();
        this.startPosition = fieldToDraw.getStartPosition();
        this.finishPosition = fieldToDraw.getFinishPosition();
        maxCellsX = fieldToDraw.getSizeX();
        maxCellsY = fieldToDraw.getSizeY();
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        this.width = (right - left)/(float) maxCellsX;;
        this.height = (bottom - top)/(float) maxCellsY;;
    }

    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawGrid(canvas);
        drawField(canvas);
        drawStartAndFinish(canvas);
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

            case FinishCell: {
                cellColour.setColor(getResources().getColor(R.color.finish));
                canvas.drawCircle(currentX * width + width / 2, currentY * height + height / 2,
                        width / 2, cellColour);
                break;
            }
        }
    }

    private void drawStartAndFinish(Canvas canvas) {
        Paint startColor = new Paint();
        startColor.setColor(getResources().getColor(R.color.start));
        canvas.drawCircle(startPosition.getX() * width + width / 2, startPosition.getY() * height + height / 2,
                width / 2, startColor);

        Paint finishColor = new Paint();
        finishColor.setColor(getResources().getColor(R.color.finish));
        canvas.drawCircle(finishPosition.getX() * width + width / 2, finishPosition.getY() * height + height / 2,
                width / 2, finishColor);
    }

    public void updateField(Field updatedField) {
        this.gridToDraw = updatedField.getGrid();
        this.maxCellsX = updatedField.getSizeX();
        this.maxCellsY = updatedField.getSizeY();
        this.startPosition = updatedField.getStartPosition();
        this.finishPosition = updatedField.getFinishPosition();
        this.invalidate();
    }

    public float getCellWidth() {
        return this.width;
    }

    public float getCellHeight() {
        return this.height;
    }
}