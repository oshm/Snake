package com.games.oleg.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.games.oleg.snake.back.models.Field;
import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellType;
import com.games.oleg.snake.back.models.cells.FinishCell;
import com.games.oleg.snake.back.models.cells.StartCell;
import com.games.oleg.snake.back.threads.GameLoopThread;

/**
 * Created by oleg.shlemin on 20.04.2015.
 */
public class NewGameView extends SurfaceView implements SurfaceHolder.Callback  {
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;

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


    public NewGameView(Context context, Field fieldToDraw) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.gridToDraw = fieldToDraw.getGrid();
        this.startPosition = fieldToDraw.getStartPosition();
        if (startCell == null) {
            startCell = new StartCell(CellType.StartCell, context);
        }
        this.finishPosition = fieldToDraw.getFinishPosition();
        maxCellsX = fieldToDraw.getSizeX();
        maxCellsY = fieldToDraw.getSizeY();
        backgroundImage = context.getResources().getDrawable(R.drawable.game_grass_background);
        this.setWillNotDraw(false);

        /*
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        */
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        this.width = (right - left) / (float) maxCellsX;
        this.height = (bottom - top) / (float) maxCellsY;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoopThread = new GameLoopThread(this);
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        // завершаем работу потока
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
        }
    }

    private void drawBackground(Canvas canvas) {
        backgroundImage.setBounds(canvas.getClipBounds());
        backgroundImage.draw(canvas);
    }

    public void drawGame(Canvas canvas) {
        drawBackground(canvas);
        drawGrid(canvas);
        drawField(canvas);
        drawStartAndFinish(canvas);
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
    }

    public float getCellWidth() {
        return this.width;
    }

    public float getCellHeight() {
        return this.height;
    }

    public void setDrawThreadRunning(boolean run) {
        gameLoopThread.setRunning(run);
    }

    public void updateStart(int startState) {
        if (startCell == null) {
            startCell = new StartCell(CellType.StartCell, getContext());
        }
        startCell.setState(startState);
    }

}
