package ru.job4j.ood.warehouse.quality;

import org.junit.Test;
import ru.job4j.ood.warehouse.Food;
import ru.job4j.ood.warehouse.foods.Milk;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.ood.warehouse.quality.TestHelpers.stubDate;

public class RuleBasedDestinationSorterTest {

    private final StoreCycleCalculator calculator = new SimpleStoreCycleCalculator(25, 75, stubDate(10));

    @Test
    public void whenAddRuleThenSortReturnsCorrectDestinationId() {
        RuleBasedDestinationSorter<Food> sorter = new RuleBasedDestinationSorter<>(calculator);
        sorter.setRule(StoreCycle.FOR_SALE, DestId.SHOP);
        DestId result = sorter.sort(new Milk(stubDate(1), stubDate(20), null));
        assertThat(result, is(DestId.SHOP));
    }

    @Test
    public void whenAddRuleWithActionThenFoodIsDiscounted() {
        RuleBasedDestinationSorter<Food> sorter = new RuleBasedDestinationSorter<>(calculator);
        Consumer<Food> discounter = food -> food.setDiscount(BigDecimal.valueOf(0.3));
        sorter.setRule(StoreCycle.DISCOUNT_SALE, DestId.SHOP, discounter);
        Milk milk = new Milk(stubDate(1), stubDate(11), null);
        DestId result = sorter.sort(milk);
        assertThat(result, is(DestId.SHOP));
        assertThat(milk.getDiscount(), is(BigDecimal.valueOf(0.3)));
    }

}
