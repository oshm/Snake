package com.games.oleg.snake.back.cells;
import com.games.oleg.snake.back.Position;

/**
 * Created by oleg on 26.10.14.
 */
public class StartCell extends Cell {
    public StartCell(int x, int y) {
        super(x, y);
    }

    public StartCell(Position position) { super(position); }
}
