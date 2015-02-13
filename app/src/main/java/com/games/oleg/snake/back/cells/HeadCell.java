package com.games.oleg.snake.back.cells;
import com.games.oleg.snake.back.Position;

/**
 * Created by oleg on 26.10.14.
 */
public class HeadCell extends Cell {
    public HeadCell(int x, int y) {
        super(x, y);
    }

    public HeadCell(Position position) { super(position); }
}
