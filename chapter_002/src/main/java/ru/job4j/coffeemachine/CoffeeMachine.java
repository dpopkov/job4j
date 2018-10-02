package ru.job4j.coffeemachine;

import java.util.Arrays;

public class CoffeeMachine {
    /**
     * Accessible coins.
     */
    private static final int[] COINS = {10, 5, 2, 1};
    /**
     * Maximum number of coins in returned change.
     */
    private static final int MAXIMUM_COINS = 10;

    /**
     * Returns change for the specified value and price.
     *
     * @param value value of banknote
     * @param price coffee price
     * @return array of returned coins
     * @throws CoinLimitExceededException when number of coins in change is too large
     * @throws IllegalArgumentException when value is less than price
     */
    public int[] changes(int value, int price) {
        if (value < price) {
            throw new IllegalArgumentException("Value can not be less than price");
        }
        int[] coinArray = new int[MAXIMUM_COINS];
        int count = 0;
        int changeValue = value - price;
        for (int i = 0; changeValue > 0 && i < COINS.length; i++) {
            int currentCoin = COINS[i];
            while (changeValue > 0 && currentCoin <= changeValue) {
                if (count == MAXIMUM_COINS - 1) {
                    throw new CoinLimitExceededException(
                            String.format("Machine can not give more than %d coins", MAXIMUM_COINS));
                }
                coinArray[count++] = currentCoin;
                changeValue -= currentCoin;
            }
        }
        return Arrays.copyOf(coinArray, count);
    }
}
