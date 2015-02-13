package com.games.oleg.snake.back.cells;
import com.games.oleg.snake.back.Position;

/**
 * Created by oleg on 26.10.14.
 */
public class Cell implements ICell{
    private Position position;

    public Cell(int x, int y) {
        this.position = new Position(x, y);
    }

    public Cell(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }



}
