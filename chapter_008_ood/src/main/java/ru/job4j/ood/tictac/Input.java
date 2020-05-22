package ru.job4j.ood.tictac;

/**
 * Represents input for game.
 */
public interface Input {
    /** Requests position for the next move using the specified prompt. */
    Position requestPosition(String prompt);
}
