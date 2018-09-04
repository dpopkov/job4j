package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private enum Direction {
        RIGHT(1, 0), DOWN(0, 1), DOWN_RIGHT(1, 1), DOWN_LEFT(-1, 1);

        private final int deltaX;
        private final int deltaY;

        Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }

    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        return rowWins(predicate) || columnWins(predicate) || diagonalWins(predicate);
    }

    private boolean rowWins(Predicate<Figure3T> predicate) {
        boolean winner = false;
        for (int row = 0; !winner && row < table.length; row++) {
            winner = fillBy(predicate, 0, row, Direction.RIGHT);
        }
        return winner;
    }

    private boolean columnWins(Predicate<Figure3T> predicate) {
        boolean winner = false;
        for (int col = 0; !winner && col < table[0].length; col++) {
            winner = fillBy(predicate, col, 0, Direction.DOWN);
        }
        return winner;
    }

    private boolean diagonalWins(Predicate<Figure3T> predicate) {
        return fillBy(predicate, 0, 0, Direction.DOWN_RIGHT)
                || fillBy(predicate, this.table[0].length - 1, 0, Direction.DOWN_LEFT);
    }

    private boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, Direction direction) {
        boolean result = true;
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < this.table.length; i++) {
            Figure3T cell = this.table[startY][startX];
            startX += direction.deltaX;
            startY += direction.deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean hasGap() {
        boolean gapFound = false;
        Figure3T cell;
        for (int row = 0; !gapFound && row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                cell = table[row][col];
                if (!cell.hasMarkO() && !cell.hasMarkX()) {
                    gapFound = true;
                    break;
                }
            }
        }
        return gapFound;
    }
}
