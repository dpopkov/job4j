package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Implements console input system.
 */
public class ConsoleInput implements Input {
    /**
     * Receives responses from console input stream.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a question on the console and retrieves a response.
     * @param question question
     * @return response
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
