package com.games.oleg.snake.back.models;

import com.games.oleg.snake.back.models.Position;
import com.games.oleg.snake.back.models.cells.Cell;
import com.games.oleg.snake.back.models.cells.CellOrientation;

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

    // Step back. Last body is head now.
    public void moveBackForHead() {
        Position lastBody = bodyPositions.get(bodyPositions.size()-1);
        headPosition = lastBody;
        bodyPositions.remove(lastBody);
    }

    public Position getLastBodyPosition() {
        if (bodyPositions.size()>0)
            return bodyPositions.get(bodyPositions.size()-1);

        return new Position(-1,-1);
    }

    public Position getPreLastBodyPosition() {
        if (bodyPositions.size()>1)
            return bodyPositions.get(bodyPositions.size()-2);

        return new Position(-1,-1);
    }

    public Position getFirstBodyPosition() {
        if (bodyPositions.size()>0)
            return bodyPositions.get(0);

        return new Position(-1,-1);
    }

    public int getSnakeLen() {
        return bodyPositions.size();
    }

}
