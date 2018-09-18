package ru.job4j.tracker;

/**
 * Implements {@link Input} for testing purposes.
 */
public class StubInput implements Input {
    /**
     * Sequence of prepared testing answers.
     */
    private final String[] answers;
    /**
     * Index of next answer, it is used and incremented
     * in {@link StubInput#ask(String) ask} method.
     */
    private int position;

    /**
     * Constructs instance of {@code StubInput} and initializes it with array of answers.
     * @param answers array of answers
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Retrieves next response to testing question.
     * @param question not used
     * @return next response from a sequence of prepared answers
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * Retrieves next integer response to testing question.
     * @param question question
     * @param range allowable range of responses
     * @return next integer response from a sequence of prepared answers
     * @throws MenuOutException when answer is not in specified range
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
