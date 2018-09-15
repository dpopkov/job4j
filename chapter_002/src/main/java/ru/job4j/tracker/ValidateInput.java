package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
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
                value = super.ask(question, range);
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
