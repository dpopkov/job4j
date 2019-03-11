package ru.job4j.io.netw.bot;

/**
 * Represents an oracle that can reply to requests.
 */
public interface Oracle {
    /**
     * Provides a response to a request.
     * @param request a sentence representing request to the oracle
     * @return response of the oracle
     */
    String reply(String request);
}
