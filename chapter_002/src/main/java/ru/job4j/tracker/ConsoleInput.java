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

    /**
     * Displays a question on the console and retrieves integer response within specified range.
     * @param question question
     * @param range allowable range of responses
     * @return response
     * @throws MenuOutException when entered response does not match the specified range
     */
    @Override
    public int ask(String question, int[] range) {
        int answer = Integer.parseInt(this.ask(question));
        boolean inRange = false;
        for (int n : range) {
            if (answer == n) {
                inRange = true;
                break;
            }
        }
        if (inRange) {
            return answer;
        } else {
            throw new MenuOutException("Out of menu range: " + answer);
        }
    }
}
