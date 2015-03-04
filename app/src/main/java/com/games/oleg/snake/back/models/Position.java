package com.games.oleg.snake.back.models;

/**
 * Created by oleg on 28.10.14.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
