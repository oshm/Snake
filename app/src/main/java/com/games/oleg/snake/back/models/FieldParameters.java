package com.games.oleg.snake.back.models;

import java.util.ArrayList;

/**
 * Created by oleg on 05.04.15.
 */
public class FieldParameters {
    public int sizeX;
    public int sizeY;
    public int startX;
    public int startY;
    public int finishX;
    public int finishY;
    ArrayList<Position> obstacles;

    public FieldParameters(int sizeX, int sizeY, int startX, int startY,
                           int finishX, int finishY, ArrayList<Position> obstacles) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.startX = startX;
        this.startY = startY;
        this.finishX = finishX;
        this.finishY = finishY;
        this.obstacles = obstacles;
    }
}
