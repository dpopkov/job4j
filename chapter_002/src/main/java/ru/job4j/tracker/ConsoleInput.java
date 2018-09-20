package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * Implements console input system.
 */
public class ConsoleInput implements Input {
    /**
     * Receives responses from console input stream.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * List of validators checking input values.
     */
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private final List<BiConsumer<Integer, int[]>> biValidators = Arrays.asList(
            (value, range) -> {
                if (valueIsNotInRange(value, range)) {
                    throw new MenuOutException("Out of menu range: " + value);
                }
            }
    );

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
        this.biValidators.forEach(action -> action.accept(answer, range));
        return answer;
    }

    /**
     * Checks whether value is not in the specified range.
     * @param value verified value
     * @param range allowed range
     * @return true if not in range, false otherwise
     */
    public static boolean valueIsNotInRange(int value, int[] range) {
        boolean out = true;
        for (int n : range) {
            if (value == n) {
                out = false;
                break;
            }
        }
        return out;
    }
}
