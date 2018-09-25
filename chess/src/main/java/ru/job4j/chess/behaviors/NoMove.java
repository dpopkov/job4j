package ru.job4j.chess.behaviors;

import ru.job4j.chess.figures.Cell;

/**
 * Not moving.
 */
public class NoMove implements MovingBehavior {
    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[0];
    }
}
