package ru.job4j.coffeemachine;

public class CoinLimitExceededException extends RuntimeException {
    public CoinLimitExceededException(String message) {
        super(message);
    }
}
