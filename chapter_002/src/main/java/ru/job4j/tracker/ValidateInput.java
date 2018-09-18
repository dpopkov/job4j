package ru.job4j.tracker;

/**
 * Implements validating input system.
 */
public class ValidateInput implements Input {
    private final Input input;

    /**
     * Constructs {@code ValidateInput} instance using the specified underlying input stream.
     * @param input the underlying input stream
     */
    public ValidateInput(Input input) {
        this.input = input;
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
                System.out.println("Please select key from the menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid integer value.");
            }
        } while (inValid);
        return value;
    }
}
