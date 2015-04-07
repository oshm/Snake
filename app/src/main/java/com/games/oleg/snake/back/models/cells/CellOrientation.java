package com.games.oleg.snake.back.models.cells;

/**
 * Created by oleg.shlemin on 02.04.2015.
 */
public enum CellOrientation {
    Up (0),
    Down (180),
    Left(270),
    Right(90),
    Invariant(-1);

    private int numVal;

    CellOrientation(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
