package ru.job4j.io.netw.bot;

import java.util.Map;

/**
 * Implements an {@link Oracle} that uses a collection of possible responses.
 */
public class WiseOracle implements Oracle {
    /** Regular expression used to split a request to words. */
    private static final String SPLIT_REGEX = "\\s|\\?";
    /** Map where key represents a keyword, and value represents an answer. */
    private final Map<String, String> answers;
    /** The default response to questions containing no keywords. */
    private String defaultResponse = "I don't know";

    /**
     * Constructs an oracle and initializes it with the answers.
     * @param answers collection of keyword-answer pairs
     */
    public WiseOracle(Map<String, String> answers) {
        this.answers = answers;
        defaultResponse = answers.getOrDefault("default", defaultResponse);
    }

    /**
     * Provides a response to a request.
     * @param request a sentence representing request to the oracle
     * @return response of the oracle
     */
    @Override
    public String reply(String request) {
        String[] words = request.split(SPLIT_REGEX);
        for (String key : words) {
            String answer = answers.get(key);
            if (answer != null) {
                return answer;
            }
        }
        return defaultResponse;
    }
}
