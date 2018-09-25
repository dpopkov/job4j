package ru.job4j.chess.behaviors;

import ru.job4j.chess.figures.Cell;

/**
 * Declares method which must define moving behavior of a figure.
 */
public interface MovingBehavior {
    /**
     * Returns array of cells that the concrete type of figure must pass through.
     * @param source source cell
     * @param dest destination cell
     * @return array of cells which may be empty if the movement is impossible
     */
    Cell[] way(Cell source, Cell dest);
}
