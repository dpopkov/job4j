package ru.job4j.tracker;

/**
 * Thrown when selected key does not match the menu range.
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String message) {
        super(message);
    }
}
