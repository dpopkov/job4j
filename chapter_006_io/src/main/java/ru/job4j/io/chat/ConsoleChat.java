package ru.job4j.io.chat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Console chat that allows a user to enter phrases and prints
 * responses from input file.
 * The whole conversation is written to a log file.
 */
public class ConsoleChat {
    /** Path to a file that contains phrases. */
    private final Path inputFile;
    /** Path to a log file. */
    private final Path logFile;
    /** A random generator that is used to choose response phrases. */
    private Random random;

    /**
     * Creates and initializes the chat with the specified input and log files.
     * @param inputFile path to input file
     * @param logFile path to log file
     */
    public ConsoleChat(Path inputFile, Path logFile) {
        this.inputFile = inputFile;
        this.logFile = logFile;
        random = new Random();
    }

    /**
     * Starts the chat.
     * @throws IOException if an I/O error occurs.
     */
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        List<String> answers = Files.readAllLines(inputFile);
        Chat chat = new Chat(scanner::nextLine,
                () -> answers.get(random.nextInt(answers.size())),
                System.out::println,
                new SimpleLogger(logFile));
        chat.start();
    }

    /**
     * Sets random with fixed seed for testing purposes.
     * @param random random with fixed seed
     */
    void setRandom(Random random) {
        this.random = random;
    }
}
