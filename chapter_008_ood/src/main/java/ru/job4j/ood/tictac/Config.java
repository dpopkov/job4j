package ru.job4j.ood.tictac;

/**
 * Represents configuration of the application.
 */
public interface Config {
    /** Returns size of the grid. */
    int getGridSize();
    /** Returns type of the first player. */
    PlayerType getFirstPlayer();
    /** Return type of the second player. */
    PlayerType getSecondPlayer();
    /** Returns number of marks in line that wins the game. */
    int getWinLineLength();
}
