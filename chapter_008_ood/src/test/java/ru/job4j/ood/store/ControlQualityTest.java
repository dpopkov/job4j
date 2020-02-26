package ru.job4j.ood.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.store.foods.Milk;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
