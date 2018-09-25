package ru.job4j.chess.behaviors;

import ru.job4j.chess.figures.Cell;

/**
 * Moving only diagonally.
 */
public class DiagonalMove implements MovingBehavior {
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (!isDiagonal(source, dest)) {
            return steps;
        } else {
            int x = source.x;
            int y = source.y;
            steps = new Cell[Math.abs(dest.x - x)];
            int deltaX = x < dest.x ? 1 : -1;
            int deltaY = y < dest.y ? 1 : -1;
            for (int i = 0; i < steps.length; i++) {
                x += deltaX;
                y += deltaY;
                steps[i] = Cell.findByXY(x, y);
            }
        }
        return steps;
    }

    private boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }
}
