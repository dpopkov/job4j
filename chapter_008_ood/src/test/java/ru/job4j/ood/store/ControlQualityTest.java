package ru.job4j.ood.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.store.foods.Milk;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.2);

    private final LocalDate now = LocalDate.now();
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;
    private ControlQuality control;

    @Before
    public void setup() {
        Distribution distribution = new Distribution(new SimpleStoreCycleCalculator(25, 75, now));
        warehouse = new Warehouse(distribution.warehouseStrategy());
        shop = new Shop(distribution.shopStrategy(), distribution.discountStrategy(), DISCOUNT);
        trash = new Trash(distribution.trashStrategy());
        control = new ControlQuality(List.of(warehouse, shop, trash));
    }

    @Test
    public void whenShelfLifeLessThan25ThenMoveToWarehouse() {
        Milk milk = new Milk(now.minusDays(1), now.plusDays(4), null);
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), contains(milk));
        assertThat(shop.takeAll(), not(contains(milk)));
        assertThat(trash.takeAll(), not(contains(milk)));
    }

    @Test
    public void whenShelfLife25To75ThenMoveToShop() {
        Milk milk = new Milk(now.minusDays(2), now.plusDays(2), null);
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), not(contains(milk)));
        assertThat(shop.takeAll(), contains(milk));
        assertThat(trash.takeAll(), not(contains(milk)));
    }

    @Test
    public void whenShelfLifeGreaterThan75ThenDiscountAndMoveToShop() {
        Milk milk = new Milk(now.minusDays(4), now.plusDays(1), null);
        assertNull(milk.getDiscount());
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), not(contains(milk)));
        assertThat(shop.takeAll(), contains(milk));
        assertThat(trash.takeAll(), not(contains(milk)));
        assertThat(milk.getDiscount(), is(DISCOUNT));
    }

    @Test
    public void whenShelfLifeExpiresThenMoveToTrash() {
        Milk milk = new Milk(now.minusDays(4), now.minusDays(1), null);
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), not(contains(milk)));
        assertThat(shop.takeAll(), not(contains(milk)));
        assertThat(trash.takeAll(), contains(milk));
    }
}
