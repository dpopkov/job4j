package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Implements validating input system.
 */
public class ValidateInput implements Input {
    private final Input input;
    private final Consumer<String> console;

    /**
     * Constructs {@code ValidateInput} instance using the specified underlying input stream
     * and consumer which receives invalid input messages.
     * @param input the underlying input stream
     * @param console messages consumer
     */
    public ValidateInput(Input input, Consumer<String> console) {
        this.input = input;
        this.console = console;
    }

    @Override
    public String ask(String question) {
        return input.ask(question);
    }

    /**
     * Displays a question on the console and retrieves integer response within specified range.
     * Repeats question until valid value is entered.
     * @param question question
     * @param range allowable range of responses
     * @return valid response
     */
    @Override
    public int ask(String question, int[] range) {
        int value = -1;
        boolean inValid = true;
        do {
            try {
                value = input.ask(question, range);
                inValid = false;
            } catch (MenuOutException moe) {
                console.accept("Please select key from the menu.");
            } catch (NumberFormatException nfe) {
                console.accept("Please enter valid integer value.");
            }
        } while (inValid);
        return value;
    }
}
