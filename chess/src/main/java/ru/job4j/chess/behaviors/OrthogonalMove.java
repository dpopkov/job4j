package ru.job4j.chess.behaviors;

import ru.job4j.chess.figures.Cell;

/**
 * Moving only orthogonally (vertically or horizontally).
 */
public class OrthogonalMove implements MovingBehavior {
    @Override
    public Cell[] way(Cell source, Cell dest) {
        throw new UnsupportedOperationException();
    }
}
