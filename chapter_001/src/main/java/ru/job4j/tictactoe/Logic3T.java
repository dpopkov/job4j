package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
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
        /* Checking rows of table */
        boolean winner = false;
        for (int row = 0; !winner && row < table.length; row++) {
            winner = true;
            for (int col = 0; col < table[row].length; col++) {
                if (!predicate.test(table[row][col])) {
                    winner = false;
                    break;
                }
            }
        }
        if (winner) {
            return true;
        }

        /* Checking columns of table */
        winner = false;
        for (int col = 0; !winner && col < table[0].length; col++) {
            winner = true;
            for (int row = 0; row < table.length; row++) {
                if (!predicate.test(table[row][col])) {
                    winner = false;
                    break;
                }
            }
        }
        if (winner) {
            return true;
        }

        /* Checking both diagonals of table */
        winner = true;
        boolean winner2 = true;
        for (int row = 0, col = 0, col2 = table[row].length - 1;
                 (winner || winner2) && row < table.length;
                 row++, col++, col2--) {
            if (!predicate.test(table[row][col])) {
                winner = false;
            }
            if (!predicate.test(table[row][col2])) {
                winner2 = false;
            }
        }
        return winner || winner2;
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
