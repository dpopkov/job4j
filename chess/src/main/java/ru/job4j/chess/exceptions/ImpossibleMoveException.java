package ru.job4j.chess.exceptions;

import ru.job4j.chess.figures.Cell;

public class ImpossibleMoveException extends Exception {
    public ImpossibleMoveException() {
    }

    public ImpossibleMoveException(String message) {
        super(message);
    }

    public ImpossibleMoveException(Cell source, Cell dest) {
        this(String.format("Cell %s can not be reached from cell %s in one move.", dest, source));
    }
}
