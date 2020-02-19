package ru.job4j.ood.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.store.foods.Milk;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WarehouseTest {
    private final LocalDate now = LocalDate.now();
    private Warehouse warehouse;

    @Before
    public void setup() {
        Distribution distribution = new Distribution(new SimpleStoreCycleCalculator(25, 75, now));
        warehouse = new Warehouse(distribution.warehouseStrategy());
    }

    @Test
    public void testAdd() {
        Milk milk = new Milk(now.minusDays(1), now.plusDays(4), BigDecimal.valueOf(80));
        warehouse.add(milk);
        assertThat(warehouse.takeAll().contains(milk), is(true));
    }

    @Test
    public void whenFoodBelow25ThenAccepts() {
        Milk milk = new Milk(now.minusDays(1), now.plusDays(4), BigDecimal.valueOf(80));
        assertThat(warehouse.accepts(milk), is(true));
    }

    @Test
    public void whenFoodFrom25ThenRejects() {
        Milk milk = new Milk(now.minusDays(1), now.plusDays(1), BigDecimal.valueOf(80));
        assertThat(warehouse.accepts(milk), is(false));
    }
}
