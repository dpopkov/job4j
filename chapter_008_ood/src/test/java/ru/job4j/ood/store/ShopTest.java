package ru.job4j.ood.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.store.foods.Meat;
import ru.job4j.ood.store.foods.Milk;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ShopTest {
    private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.3);

    private final LocalDate now = LocalDate.now();
    private Shop shop;

    @Before
    public void setup() {
        Distribution distribution = new Distribution(new SimpleStoreCycleCalculator(25, 75, now));
        shop = new Shop(distribution.shopStrategy(), distribution.discountStrategy(), DISCOUNT);
    }

    @Test
    public void whenFoodFrom25To75ThenAcceptsAndAdds() {
        Milk milk = new Milk(now.minusDays(1), now.plusDays(3), BigDecimal.valueOf(80));
        assertThat(shop.accepts(milk), is(true));
        shop.add(milk);
        assertThat(shop.takeAll(), contains(milk));
    }

    @Test
    public void whenFoodFrom75to100ThenAcceptsAddsAndDiscounts() {
        Milk milk = new Milk(now.minusDays(3), now.plusDays(1), BigDecimal.valueOf(80));
        assertThat(shop.accepts(milk), is(true));
        assertNull(milk.getDiscount());
        shop.add(milk);
        assertThat(shop.takeAll(), contains(milk));
        assertThat(milk.getDiscount(), is(DISCOUNT));
    }

    @Test
    public void whenFoodBelow25orOver100ThenRejects() {
        Milk milk = new Milk(now.minusDays(1), now.plusDays(4), BigDecimal.valueOf(80));
        assertThat(shop.accepts(milk), is(false));
        Meat meat = new Meat(now.minusDays(4), now.minusDays(1), BigDecimal.valueOf(400));
        assertThat(shop.accepts(meat), is(false));
    }
}
