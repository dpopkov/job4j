package ru.job4j.converter;

/**
 * Currency converter.
 */
public class Converter {

    private static final int DOLLAR_TO_RUBLE_RATE = 60;
    private static final int EURO_TO_RUBLE_RATE = 70;

    /**
     * Converts rubles into euros.
     * @param value rubles
     * @return euros
     */
    public int rubleToEuro(int value) {
        return value / EURO_TO_RUBLE_RATE;
    }

    /**
     * Converts euros into rubles.
     * @param value euros
     * @return rubles
     */
    public int euroToRuble(int value) {
        return value * EURO_TO_RUBLE_RATE;
    }

    /**
     * Converts rubles into dollars.
     * @param value rubles
     * @return dollars
     */
    public int rubleToDollar(int value) {
        return value / DOLLAR_TO_RUBLE_RATE;
    }

    /**
     * Converts dollars to rubles.
     * @param value dollars
     * @return rubles
     */
    public int dollarToRuble(int value) {
        return value * DOLLAR_TO_RUBLE_RATE;
    }
}
