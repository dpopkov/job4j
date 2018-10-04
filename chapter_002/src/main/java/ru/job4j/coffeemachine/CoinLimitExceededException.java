package ru.job4j.coffeemachine;

/**
 * Thrown to indicate that the amount of coins exceeds limit on the number of returned coins.
 */
public class CoinLimitExceededException extends RuntimeException {
    /**
     * Constructs a new coin limit exception with the specified detail message.
     * @param message the detail message
     */
    public CoinLimitExceededException(String message) {
        super(message);
    }
}
