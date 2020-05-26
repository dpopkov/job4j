package ru.job4j.ood.tictac;

/**
 * Represents grid that can be changed.
 */
public interface GameGrid extends GridView {
    /** Changes mark of the grid cell at the specified position. */
    void changeCell(Position position, Mark mark);

    /**
     * Tries to find a winner having the specified number of adjacent marks.
     * @param lineLength number of adjacent marks that should be on one line.
     * @return mark of the winner or null if there is no winner yet
     */
    Mark findWinningMark(int lineLength);
}
