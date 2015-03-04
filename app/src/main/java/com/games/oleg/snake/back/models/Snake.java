package com.games.oleg.snake.back.models;

import com.games.oleg.snake.back.models.Position;

import java.util.ArrayList;

/**
 * Created by oleg on 26.10.14.
 */
public class Snake {
    private Position headPosition;
    private ArrayList<Position> bodyPositions;

    public Snake(Position headPosition) {
        this.headPosition = headPosition;
        this.bodyPositions = new ArrayList <Position>();
    }

    public Position getHeadPosition() {
        return  this.headPosition;
    }

    // Head moved: old head is body now, head updated
    public void setHead(Position newHeadPosition) {
        bodyPositions.add(this.headPosition);
        this.headPosition = newHeadPosition;


    }

}
