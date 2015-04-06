package com.games.oleg.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

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
    private Cell[][] gridToDraw;
    private Position startPosition;
    private Position finishPosition;
    private int maxCellsX;        // number of cells in field horizontally
    private int maxCellsY;        //number of cells in field vertically

     private float width;            //width of one square
    private float height;           //height of one square
    float x = -30;
    float y = -30;

    private Drawable backgroundImage;
    private Drawable obstacleImage;


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
        backgroundImage = context.getResources().getDrawable(R.drawable.game_grass_background);
        //obstacleImage = context.getResources().getDrawable(R.drawable.stone);
        Bitmap obstacleBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone);
        obstacleBitmap = makeBitmapTransparent(obstacleBitmap, Color.WHITE);
        obstacleImage = new BitmapDrawable(getResources(),obstacleBitmap);
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
        /*
        Paints background to colour
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.game_background));
        canvas.drawRect(0,0, getWidth(), getHeight(), background);
        */
        Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

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
                Rect bounds = new Rect((int)(currentX*width),(int)(currentY*height),
                        (int)(currentX*width + width), (int)(currentY*height+height));
                cellToDraw.drawCell(canvas, bounds);
                break;
            }

            case FinishCell: {
                Rect bounds = new Rect((int)(currentX*width),(int)(currentY*height),
                        (int)(currentX*width + width), (int)(currentY*height+height));
                cellToDraw.drawCell(canvas, bounds);
                break;
            }
        }
    }

    private void drawStartAndFinish(Canvas canvas) {
        Cell startCell = new StartCell(CellType.StartCell, getContext());
        Rect bounds = new Rect((int)(startPosition.getX()*width),(int)(startPosition.getY()*height),
                (int)(startPosition.getX()*width + width), (int)(startPosition.getY()*height+height));
        startCell.drawCell(canvas, bounds);

        Cell finishCell = new FinishCell(CellType.FinishCell, getContext());
        bounds = new Rect((int)(finishPosition.getX()*width),(int)(finishPosition.getY()*height),
                (int)(finishPosition.getX()*width + width), (int)(finishPosition.getY()*height+height));
        finishCell.drawCell(canvas, bounds);
    }

    // Convert transparentColor to be transparent in a Bitmap. TODO:Should moved out of here.
    private Bitmap makeBitmapTransparent(Bitmap bit, int transparentColor) {
        int width =  bit.getWidth();
        int height = bit.getHeight();
        Bitmap myBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int [] allpixels = new int [ myBitmap.getHeight()*myBitmap.getWidth()];
        bit.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(),myBitmap.getHeight());
        myBitmap.setPixels(allpixels, 0, width, 0, 0, width, height);

        for(int i =0; i<myBitmap.getHeight()*myBitmap.getWidth();i++){
            if( allpixels[i] == transparentColor)

                allpixels[i] = Color.alpha(Color.TRANSPARENT);
        }

        myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
        return myBitmap;
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