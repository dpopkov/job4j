package ru.job4j.ood.tictac;

/**
 * Represents grid that can be changed.
 */
public interface GameGrid extends GridView {
    /** Sets the specified mark at the specified position. */
    void setMark(Position position, Mark mark);

    /**
     * Checks whether the grid has a winner having the specified number
     * of adjacent marks.
     * @param lineLength number of adjacent marks that should be on one line.
     * @return mark of the winner or null if there is no winner yet
     */
    Mark getWinner(int lineLength);
}
