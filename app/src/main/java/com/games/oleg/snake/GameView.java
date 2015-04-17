package com.games.oleg.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.games.oleg.snake.back.threads.BackgroundAnimationThread;
import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellType;
import com.games.oleg.snake.back.models.cells.FinishCell;
import com.games.oleg.snake.back.models.cells.StartCell;

/**
 * Created by oleg on 09.03.15.
 */
public class GameView extends View {
    private final GameActivity gameActivity;
    //private BackgroundAnimationThread animationThread;
    private Cell[][] gridToDraw;
    private StartCell startCell;
    private Position startPosition;
    private Position finishPosition;
    private int maxCellsX;        // number of cells in field horizontally
    private int maxCellsY;        //number of cells in field vertically

    private float width;            //width of one square
    private float height;           //height of one square
    float x = -30;
    float y = -30;

    private Drawable backgroundImage;


    public GameView(Context context, Field fieldToDraw) {
        super(context);
        this.gameActivity = (GameActivity) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.gridToDraw = fieldToDraw.getGrid();
        this.startPosition = fieldToDraw.getStartPosition();
        if (startCell == null) {
            startCell = new StartCell(CellType.StartCell, getContext());
        }
        this.finishPosition = fieldToDraw.getFinishPosition();
        maxCellsX = fieldToDraw.getSizeX();
        maxCellsY = fieldToDraw.getSizeY();
        backgroundImage = context.getResources().getDrawable(R.drawable.game_grass_background);
        this.setWillNotDraw(false);
        //animationThread = new BackgroundAnimationThread(this);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        this.width = (right - left) / (float) maxCellsX;
        this.height = (bottom - top) / (float) maxCellsY;
        /*
        animationThread.setRunning(true);
        if (!animationThread.isAlive())
            animationThread.start();
            */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawGrid(canvas);
        drawField(canvas);
        drawStartAndFinish(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawBackground(canvas);
        drawGrid(canvas);
        drawField(canvas);
        drawStartAndFinish(canvas);
    }



    public void updateTouched(float x, float y) {
        //TODO: Paste some animation here!
        this.x = x;
        this.y = y;
        this.invalidate();
    }

    private void drawBackground(Canvas canvas) {
        backgroundImage.setBounds(canvas.getClipBounds());
        backgroundImage.draw(canvas);
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
        // Do not draw cells if on start
        if (new Position(currentX, currentY).equals(startPosition))
            return;
        Cell cellToDraw = gridToDraw[currentY][currentX];
        Rect bounds = new Rect((int) (currentX * width), (int) (currentY * height),
                (int) (currentX * width + width), (int) (currentY * height + height));
        cellToDraw.drawCell(canvas, bounds);
    }

    private void drawStartAndFinish(Canvas canvas) {
        Rect bounds = new Rect((int)(startPosition.getX()*width),(int)(startPosition.getY()*height),
                (int)(startPosition.getX()*width + width), (int)(startPosition.getY()*height+height));
        startCell.drawCell(canvas, bounds);

        Cell finishCell = new FinishCell(CellType.FinishCell, getContext());
        bounds = new Rect((int)(finishPosition.getX()*width),(int)(finishPosition.getY()*height),
                (int)(finishPosition.getX()*width + width), (int)(finishPosition.getY()*height+height));
        finishCell.drawCell(canvas, bounds);
    }

    public void updateField(Field updatedField) {
        this.gridToDraw = updatedField.getGrid();
        this.maxCellsX = updatedField.getSizeX();
        this.maxCellsY = updatedField.getSizeY();
        this.startPosition = updatedField.getStartPosition();
        this.finishPosition = updatedField.getFinishPosition();
        this.invalidate();
    }

    public void updateStart(int startState) {
        if (startCell == null) {
            startCell = new StartCell(CellType.StartCell, getContext());
        }
        startCell.setState(startState);
        this.invalidate();
    }


    public void updateBackground() {
        try {


            this.updateStart(0);
            Thread.sleep(1000);
            this.updateStart(1);
            Thread.sleep(1000);
            this.updateStart(0);
            Thread.sleep(1000);
            this.updateStart(1);
            Thread.sleep(1000);
            this.updateStart(0);
        }
        catch (InterruptedException ie) {};
    }

    public float getCellWidth() {
        return this.width;
    }

    public float getCellHeight() {
        return this.height;
    }
}