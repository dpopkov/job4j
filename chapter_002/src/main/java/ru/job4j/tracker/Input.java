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

    /**
     * Outputs a question and retrieves integer response within specified range.
     * @param question question
     * @param range allowable range of responses
     * @return response
     */
    int ask(String question, int[] range);
}
