package ru.job4j.ood.warehouse.quality;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.warehouse.Food;
import ru.job4j.ood.warehouse.Shop;
import ru.job4j.ood.warehouse.Trash;
import ru.job4j.ood.warehouse.Warehouse;
import ru.job4j.ood.warehouse.foods.Milk;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ru.job4j.ood.warehouse.quality.TestHelpers.stubDate;

public class ControlQualityTest {
    private RuleBasedDestinationSorter<Food> strategy;
    private ControlQuality<Food> control;

    @Before
    public void setup() {
        strategy = new RuleBasedDestinationSorter<>(
                new SimpleStoreCycleCalculator(25, 75, stubDate(10)));
        control = new ControlQuality<>(strategy);
    }

    @Test
    public void whenShelfLifeLessThan25ThenMoveToWarehouse() {
        Warehouse warehouse = new Warehouse();
        Milk milk = createMilk(8, 18);
        List<Food> foods = List.of(milk);
        strategy.setRule(StoreCycle.STORAGE, DestId.WAREHOUSE);
        control.addDestination(DestId.WAREHOUSE, warehouse);
        assertThat(warehouse.contains(milk), is(false));
        control.sort(foods);
        assertThat(warehouse.contains(milk), is(true));
    }

    @Test
    public void whenShelfLife25To75ThenMoveToShop() {
        Shop shop = new Shop();
        Milk milk = createMilk(5, 15);
        List<Food> foods = List.of(milk);
        strategy.setRule(StoreCycle.FOR_SALE, DestId.SHOP);
        control.addDestination(DestId.SHOP, shop);
        assertThat(shop.contains(milk), is(false));
        control.sort(foods);
        assertThat(shop.contains(milk), is(true));
    }

    @Test
    public void whenShelfLifeGreaterThan75ThenDiscountAndMoveToShop() {
        Shop shop = new Shop();
        Milk milk = createMilk(2, 12);
        List<Food> foods = List.of(milk);
        strategy.setRule(StoreCycle.DISCOUNT_SALE, DestId.SHOP, food -> food.setDiscount(BigDecimal.valueOf(0.3)));
        control.addDestination(DestId.SHOP, shop);
        BigDecimal expectedDiscount = BigDecimal.valueOf(0.3);
        assertThat(shop.contains(milk), is(false));
        assertThat(milk.getDiscount(), is(not(expectedDiscount)));
        control.sort(foods);
        assertThat(shop.contains(milk), is(true));
        assertThat(milk.getDiscount(), is(expectedDiscount));
    }

    @Test
    public void whenShelfLifeExpiresThenMoveToTrash() {
        Trash trash = new Trash();
        Milk milk = createMilk(1, 9);
        List<Food> foods = List.of(milk);
        strategy.setRule(StoreCycle.EXPIRED, DestId.TRASH);
        control.addDestination(DestId.TRASH, trash);
        assertThat(trash.contains(milk), is(false));
        control.sort(foods);
        assertThat(trash.contains(milk), is(true));
    }

    @Test
    public void whenChangeSortingStrategyThenChangesDistribution() {
        Shop shop = new Shop();
        Milk milk = createMilk(1, 11);
        strategy.setRule(StoreCycle.DISCOUNT_SALE, DestId.SHOP, food -> food.setDiscount(BigDecimal.valueOf(0.2)));
        control.addDestination(DestId.SHOP, shop);
        BigDecimal expectedDiscount = BigDecimal.valueOf(0.2);
        assertThat(shop.contains(milk), is(false));
        assertThat(milk.getDiscount(), is(not(expectedDiscount)));
        control.sort(List.of(milk));
        assertThat(shop.contains(milk), is(true));
        assertThat(milk.getDiscount(), is(expectedDiscount));

        RuleBasedDestinationSorter<Food> savingStrategy = new RuleBasedDestinationSorter<>(
                new SimpleStoreCycleCalculator(25, 95, stubDate(10)));
        savingStrategy.setRule(StoreCycle.FOR_SALE, DestId.SHOP);
        control.setSortingStrategy(savingStrategy);
        Milk milk2 = createMilk(1, 11);
        assertThat(shop.contains(milk2), is(false));
        control.sort(List.of(milk2));
        assertThat(shop.contains(milk2), is(true));
        assertNull(milk2.getDiscount());
    }

    private Milk createMilk(int creationDay, int expirationDay) {
        return new Milk(stubDate(creationDay), stubDate(expirationDay), BigDecimal.valueOf(90));
    }
}
