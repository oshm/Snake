package com.games.oleg.snake.back.cells;
import com.games.oleg.snake.back.Position;

/**
 * Created by oleg on 26.10.14.
 */
public class BodyCell extends Cell {
    public BodyCell(int x, int y) {
        super(x, y);
    }

    public BodyCell(Position position) { super(position); }

}
