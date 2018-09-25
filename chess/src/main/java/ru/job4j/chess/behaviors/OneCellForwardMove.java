package ru.job4j.chess.behaviors;

import ru.job4j.chess.figures.Cell;

/**
 * Moving one cell forward only.
 */
public class OneCellForwardMove implements MovingBehavior {
    /**
     * Delta Y defining direction of forward movement.
     */
    private final int deltaY;

    /**
     * Constructs instance with specified delta Y which defines forward direction.
     * @param deltaY delta Y (must be 1 or -1)
     */
    public OneCellForwardMove(int deltaY) {
        this.deltaY = deltaY;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (source.y + deltaY == dest.y && source.x == dest.x) {
            steps = new Cell[] {dest};
        }
        return steps;
    }
}
