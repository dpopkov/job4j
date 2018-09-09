package ru.job4j.tracker;

/**
 * Defines input system.
 */
public interface Input {
    /**
     * Outputs a question and retrieves a response.
     * @param question question
     * @return response
     */
    String ask(String question);
}
