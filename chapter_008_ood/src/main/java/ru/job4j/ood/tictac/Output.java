package ru.job4j.ood.tictac;

/**
 * Represents output for game.
 */
public interface Output {
    /** Prints the specified game grid. */
    void printGrid(GridView grid);
    /** Prints the specified message. */
    void print(String message);
    /** Prints the specified message and newline symbol. */
    void println(String message);
}
