package ru.job4j.chess.behaviors;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Moving only diagonally.
 */
public class DiagonalMove implements MovingBehavior {
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException(source, dest);
        }
        int x = source.x;
        int y = source.y;
        Cell[] steps = new Cell[Math.abs(dest.x - x)];
        int deltaX = x < dest.x ? 1 : -1;
        int deltaY = y < dest.y ? 1 : -1;
        return IntStream.range(1, steps.length + 1)
                .mapToObj(i -> Cell.findByXY(x + i * deltaX, y + i * deltaY))
                .collect(Collectors.toList()).toArray(steps);
    }

    private boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }
}
