package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    @Test
    public void whenValueEqualsPriceThenReturnZero() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] result = machine.changes(50, 50);
        assertThat(result, is(new int[] {}));
    }

    @Test
    public void whenValue50Price40ThenReturn10() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] result = machine.changes(50, 40);
        assertThat(result, is(new int[] {10}));
    }

    @Test
    public void whenValue50Price35ThenReturn10And5() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] result = machine.changes(50, 35);
        assertThat(result, is(new int[] {10, 5}));
    }

    @Test
    public void whenValue50Price22ThenReturn10And10And5And2And1() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] result = machine.changes(50, 22);
        assertThat(result, is(new int[] {10, 10, 5, 2, 1}));
    }

    @Test(expected = CoinLimitExceededException.class)
    public void whenNeedMoreThan10CoinsThenCoinLimitExceededException() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.changes(100, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsLessThanPriceThenIllegalArgumentException() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.changes(10, 11);
    }
}
