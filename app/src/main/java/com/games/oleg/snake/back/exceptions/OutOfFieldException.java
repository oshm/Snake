package com.games.oleg.snake.back.exceptions;

import java.io.*;
/**
 * Created by oleg on 09.11.14.
 */
public class OutOfFieldException extends Exception {
    public OutOfFieldException(int value, int fieldSize, String axis) {
        System.out.format(
                axis + " out of bounds!" + axis + ": %d, field size: %d", value, fieldSize);
    }


}
