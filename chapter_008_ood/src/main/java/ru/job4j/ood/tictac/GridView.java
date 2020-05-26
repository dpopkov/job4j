package ru.job4j.ood.tictac;

/**
 * Represents grid view containing only methods that do not change the grid.
 */
public interface GridView {
    /** Returns mark at the specified position. */
    Mark markAt(Position position);
    /** Checks whether the cell the specified position is free or busy. */
    boolean isFreeAt(Position position);
    /** Returns true if all cells in the grid are busy, false otherwise. */
    boolean isFull();
    /** Returns size of the grid. */
    int size();
}
