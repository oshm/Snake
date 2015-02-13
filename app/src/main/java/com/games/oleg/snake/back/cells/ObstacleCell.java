package com.games.oleg.snake.back.cells;
import com.games.oleg.snake.back.Position;

/**
 * Created by oleg on 26.10.14.
 */
public class ObstacleCell extends Cell {
    public ObstacleCell(int x, int y) {
        super(x, y);
    }

    public ObstacleCell(Position position) { super(position); }


}