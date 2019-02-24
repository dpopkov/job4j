package ru.job4j.io.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

/**
 * A simple logger that writes messages to a log file.
 */
public class SimpleLogger implements Consumer<String> {
    /** Line separator string. */
    private static final String NL = System.lineSeparator();

    /** Path to log file. */
    private final Path path;

    /**
     * Constructs and initializes the logger with the specified path to log file.
     * The file will be created. If the file exists, then the new messages will be appended.
     * @param path path to log file
     */
    public SimpleLogger(Path path) {
        this.path = path;
    }

    /**
     * Accepts the message and appends the message to the log file.
     * @param message message to append to log file
     */
    @Override
    public void accept(String message) {
        try {
            Files.writeString(path, message + NL,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error writing message to log", e);
        }
    }
}
