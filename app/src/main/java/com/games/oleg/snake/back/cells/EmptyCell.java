package com.games.oleg.snake.back.cells;
import com.games.oleg.snake.back.Position;

/**
 * Created by oleg on 26.10.14.
 */
public class EmptyCell extends Cell {
    public EmptyCell(int x, int y) { super(x, y); }

    public EmptyCell(Position position) { super(position); }

}
