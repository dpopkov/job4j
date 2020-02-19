package ru.job4j.ood.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.store.foods.Milk;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TrashTest {
    private final LocalDate now = LocalDate.now();
    private Trash trash;

    @Before
    public void setup() {
        Distribution distribution = new Distribution(new SimpleStoreCycleCalculator(25, 75, now));
        trash = new Trash(distribution.trashStrategy());
    }

    @Test
    public void whenFoodUpto100ThenRejects() {
        Milk milk = new Milk(now.minusDays(4), now, BigDecimal.valueOf(80));
        assertThat(trash.accepts(milk), is(false));
    }

    @Test
    public void whenFoodOver100ThenAccepts() {
        Milk milk = new Milk(now.minusDays(4), now.minusDays(1), BigDecimal.valueOf(80));
        assertThat(trash.accepts(milk), is(true));
    }
}
