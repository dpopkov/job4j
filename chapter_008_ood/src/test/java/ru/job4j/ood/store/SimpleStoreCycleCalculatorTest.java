package ru.job4j.ood.store;

import org.junit.Test;
import ru.job4j.ood.store.cycle.Expirable;
import ru.job4j.ood.store.cycle.StoreCycle;
import ru.job4j.ood.store.cycle.StoreCycleCalculator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.*;
import static ru.job4j.ood.store.TestHelpers.*;

public class SimpleStoreCycleCalculatorTest {

    private final StoreCycleCalculator calc = new SimpleStoreCycleCalculator(25, 75, stubDate(10));

    @Test
    public void whenLessThan25ThenReturnsStorage() {
        assertResult(8, 18, StoreCycle.STORAGE);
    }

    @Test
    public void whenLessThan75ThenReturnsForSale() {
        assertResult(5, 15, StoreCycle.FOR_SALE);
    }

    @Test
    public void whenLessThan100ThenReturnsDiscount() {
        assertResult(1, 11, StoreCycle.DISCOUNT_SALE);
    }

    @Test
    public void whenGreaterThan100ThenReturnsExpired() {
        assertResult(1, 9, StoreCycle.EXPIRED);
    }

    private void assertResult(int creationDay, int expirationDay, StoreCycle expected) {
        Expirable e = stubExpirable(creationDay, expirationDay);
        StoreCycle result = calc.calculate(e);
        assertThat(result, is(sameInstance(expected)));
    }
}