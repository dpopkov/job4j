package ru.job4j.ood.warehouse;

import ru.job4j.ood.warehouse.foods.*;
import ru.job4j.ood.warehouse.quality.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Starting application.
 * Initializes food list and ControlQuality instance.
 */
public class ControlQualityMain {
    public static void main(String[] args) {
        List<Food> foods = initFoods();
        LocalDate today = LocalDate.of(2020, 1, 10);
        DestinationSorter<Food> sorter = initSorter(today);
        ControlQuality<Food> control = initControl(sorter);
        control.sort(foods);
    }

    private static List<Food> initFoods() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Milk(LocalDate.of(2020, 1, 10), LocalDate.of(2020, 1, 20), BigDecimal.valueOf(90)));
        foods.add(new Bread(LocalDate.of(2020, 1, 7), LocalDate.of(2020, 1, 17), BigDecimal.valueOf(30)));
        foods.add(new Cheese(LocalDate.of(2020, 1, 2), LocalDate.of(2020, 1, 12), BigDecimal.valueOf(160)));
        foods.add(new Meat(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), BigDecimal.valueOf(160)));
        return foods;
    }

    private static RuleBasedDestinationSorter<Food> initSorter(LocalDate today) {
        RuleBasedDestinationSorter<Food> sorter = new RuleBasedDestinationSorter<>(
                new SimpleStoreCycleCalculator(25, 75, today));
        sorter.setRule(StoreCycle.STORAGE, DestId.WAREHOUSE);
        sorter.setRule(StoreCycle.FOR_SALE, DestId.SHOP);
        sorter.setRule(StoreCycle.DISCOUNT_SALE, DestId.SHOP, food -> food.setDiscount(BigDecimal.valueOf(0.10)));
        sorter.setRule(StoreCycle.EXPIRED, DestId.TRASH);
        return sorter;
    }

    private static ControlQuality<Food> initControl(DestinationSorter<Food> sorter) {
        ControlQuality<Food> control = new ControlQuality<>(sorter);
        control.addDestination(DestId.WAREHOUSE, new Warehouse());
        control.addDestination(DestId.SHOP, new Shop());
        control.addDestination(DestId.TRASH, new Trash());
        return control;
    }
}
