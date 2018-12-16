package ru.job4j.chess.behaviors;

import ru.job4j.chess.figures.Cell;

/**
 * Moving one cell in all directions.
 */
public class OneCellMove implements MovingBehavior {
    @Override
    public Cell[] way(Cell source, Cell dest) {
        throw new UnsupportedOperationException();
    }
}
