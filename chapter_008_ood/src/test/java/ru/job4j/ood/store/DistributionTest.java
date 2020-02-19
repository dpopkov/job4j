package ru.job4j.ood.store;

import org.junit.Test;
import ru.job4j.ood.store.cycle.DistributionStrategy;
import ru.job4j.ood.store.foods.Bread;
import ru.job4j.ood.store.foods.Cheese;
import ru.job4j.ood.store.foods.Meat;
import ru.job4j.ood.store.foods.Milk;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DistributionTest {
    private final LocalDate now = LocalDate.now();
    private final Distribution distribution = new Distribution(new SimpleStoreCycleCalculator(25, 75, now));

    @Test
    public void whenBelow25ThenWarehouseAccepts() {
        DistributionStrategy warehouse = distribution.warehouseStrategy();
        Milk milk = new Milk(now.minusDays(1), now.plusDays(4), BigDecimal.valueOf(80));
        assertThat(warehouse.accepts(milk), is(true));
    }

    @Test
    public void whenFrom25ThenWarehouseRejects() {
        DistributionStrategy warehouse = distribution.warehouseStrategy();
        Cheese cheese = new Cheese(now.minusDays(1), now.plusDays(1), BigDecimal.valueOf(80));
        assertThat(warehouse.accepts(cheese), is(false));
    }

    @Test
    public void whenFrom25to75ThenShopAccepts() {
        DistributionStrategy shop = distribution.shopStrategy();
        Milk milk = new Milk(now.minusDays(1), now.plusDays(1), BigDecimal.valueOf(80));
        assertThat(shop.accepts(milk), is(true));
    }

    @Test
    public void whenBelow25orOver100ThenShopRejects() {
        DistributionStrategy shop = distribution.shopStrategy();
        Milk milk = new Milk(now.minusDays(1), now.plusDays(4), BigDecimal.valueOf(80));
        assertThat(shop.accepts(milk), is(false));
        Meat meat = new Meat(now.minusDays(4), now.minusDays(1), BigDecimal.valueOf(400));
        assertThat(shop.accepts(meat), is(false));
    }

    @Test
    public void whenUpto100ThenTrashRejects() {
        DistributionStrategy trash = distribution.trashStrategy();
        Bread bread = new Bread(now.minusDays(3), now, BigDecimal.valueOf(30));
        assertThat(trash.accepts(bread), is(false));
    }

    @Test
    public void whenOver100ThenTrashAccepts() {
        DistributionStrategy trash = distribution.trashStrategy();
        Bread bread = new Bread(now.minusDays(3), now.minusDays(1), BigDecimal.valueOf(30));
        assertThat(trash.accepts(bread), is(true));
    }
}
