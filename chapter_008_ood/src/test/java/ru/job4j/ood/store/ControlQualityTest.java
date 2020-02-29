package ru.job4j.ood.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.store.foods.Bread;
import ru.job4j.ood.store.foods.Cheese;
import ru.job4j.ood.store.foods.Meat;
import ru.job4j.ood.store.foods.Milk;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.2);
    public static final int WAREHOUSE_LIMIT = 25;
    public static final int SALE_LIMIT = 75;

    private final LocalDate now = LocalDate.now();
    private Store warehouse;
    private Store shop;
    private Store trash;
    private Distribution distribution;
    private ControlQuality control;

    @Before
    public void setup() {
        distribution = new Distribution(new SimpleStoreCycleCalculator(WAREHOUSE_LIMIT, SALE_LIMIT, now));
        warehouse = new Warehouse(distribution.warehouseStrategy());
        shop = new Shop(distribution.shopStrategy(), distribution.discountStrategy(), DISCOUNT);
        trash = new Trash(distribution.trashStrategy());
        control = new ControlQuality(List.of(warehouse, shop, trash));
    }

    @Test
    public void whenShelfLifeLessThan25ThenMoveToWarehouse() {
        Milk milk = makeFood(Milk.class, 1, 4);
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), contains(milk));
        assertThat(shop.takeAll(), not(contains(milk)));
        assertThat(trash.takeAll(), not(contains(milk)));
    }

    @Test
    public void whenShelfLife25To75ThenMoveToShop() {
        Milk milk = makeFood(Milk.class, 2, 2);
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), not(contains(milk)));
        assertThat(shop.takeAll(), contains(milk));
        assertThat(trash.takeAll(), not(contains(milk)));
    }

    @Test
    public void whenShelfLifeGreaterThan75ThenDiscountAndMoveToShop() {
        Milk milk = makeFood(Milk.class, 4, 1);
        assertNull(milk.getDiscount());
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), not(contains(milk)));
        assertThat(shop.takeAll(), contains(milk));
        assertThat(trash.takeAll(), not(contains(milk)));
        assertThat(milk.getDiscount(), is(DISCOUNT));
    }

    @Test
    public void whenShelfLifeExpiresThenMoveToTrash() {
        Milk milk = makeFood(Milk.class, 4, -1);
        control.sort(List.of(milk));
        assertThat(warehouse.takeAll(), not(contains(milk)));
        assertThat(shop.takeAll(), not(contains(milk)));
        assertThat(trash.takeAll(), contains(milk));
    }

    @Test
    public void whenResortAfterSomeDaysPassedThenFoodIsRedistributed() {
        Milk milk = makeFood(Milk.class, 0, 5);
        Bread bread = makeFood(Bread.class, 2, 3);
        Cheese cheese = makeFood(Cheese.class, 4, 1);
        Meat meat = makeFood(Meat.class, 5, -1);

        control.sort(List.of(milk, bread, cheese, meat));
        assertTrue(warehouse.contains(milk));
        assertTrue(shop.contains(bread));
        assertTrue(shop.contains(cheese));
        assertThat(cheese.getDiscount(), is(DISCOUNT));
        assertTrue(trash.contains(meat));

        LocalDate twoDaysHavePassed = now.plusDays(2);
        distribution.setCycleCalculator(new SimpleStoreCycleCalculator(WAREHOUSE_LIMIT, SALE_LIMIT, twoDaysHavePassed));
        control.resort();
        assertTrue(warehouse.isEmpty());
        assertThat(shop.takeAll(), containsInAnyOrder(milk, bread));
        assertThat(bread.getDiscount(), is(DISCOUNT));
        assertThat(trash.takeAll(), containsInAnyOrder(cheese, meat));
    }

    @Test
    public void whenFoodAcceptedByTwoStoresThenOnlyOneReceivesIt() {
        trash = new Trash(distribution.warehouseStrategy());
        control = new ControlQuality(List.of(warehouse, shop, trash));
        Milk milk = makeFood(Milk.class, 0, 5);
        control.sort(List.of(milk));
        assertTrue(warehouse.contains(milk));
        assertTrue(shop.isEmpty());
        assertTrue(trash.isEmpty());
    }

    private <T extends Food> T makeFood(Class<T> clazz, int createdBefore, int expiresAfter)  {
        try {
            Constructor<T> constructor = clazz.getConstructor(LocalDate.class, LocalDate.class, BigDecimal.class);
            LocalDate created = now.minusDays(createdBefore);
            LocalDate expires = now.plusDays(expiresAfter);
            return constructor.newInstance(created, expires, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to make food", e);
        }
    }
}
