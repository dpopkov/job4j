package ru.job4j.ood.tictac;

import java.util.Objects;

/**
 * Position on the game grid.
 */
public final class Position {
    private final int row;
    private final int col;

    /** Constructs the position using specified row and column. */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /** Returns row of position in the grid. */
    public int getRow() {
        return row;
    }

    /** Returns column of position in the grid. */
    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
