package com.games.oleg.snake.back;

import com.games.oleg.snake.back.cells.BodyCell;
import com.games.oleg.snake.back.cells.HeadCell;
import com.games.oleg.snake.back.cells.ICell;

import java.util.List;

/**
 * Created by oleg on 26.10.14.
 */
public class Snake {
    private HeadCell head;
    private List<BodyCell> body;

    public Snake(Position position) {
        this.head = new HeadCell(position);

    }

    public HeadCell getHead() {
        return  this.head;
    }

    // Head moved: old head is body now, head updated
    public void setHead(Position newHeadPosition) {
        body.add(new BodyCell(head.getPosition()));
        this.head = new HeadCell(newHeadPosition);


    }

    public Position getHeadPosition() {
        return this.head.getPosition();
    }


}
