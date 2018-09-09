package ru.job4j.tracker;

/**
 * Defines input system.
 */
public interface Input {
    /**
     * Outputs a questions and receives a response.
     * @param question question
     * @return response
     */
    String ask(String question);
}
