package ru.job4j.ood.store;

import ru.job4j.ood.store.cycle.DistributionStrategy;

import java.math.BigDecimal;

/**
 * Represents shop that that collects and discounts food items.
 */
public class Shop extends AbstractStore {
    private final DistributionStrategy saleStrategy;
    private final DistributionStrategy discountStrategy;
    private final BigDecimal discount;

    /**
     * Constructs shop instance with the specified strategies and discount.
     * @param saleStrategy strategy accepting food without discount
     * @param discountStrategy strategy accepting food with discount
     * @param discount discount value
     */
    public Shop(DistributionStrategy saleStrategy, DistributionStrategy discountStrategy, BigDecimal discount) {
        super("Shop");
        this.saleStrategy = saleStrategy;
        this.discountStrategy = discountStrategy;
        this.discount = discount;
    }

    /**
     * Checks if the shop can accept the specified food
     * @param food checked food
     * @return true if the store can accept the food instance, false otherwise
     */
    @Override
    public boolean accepts(Food food) {
        return saleStrategy.accepts(food) || discountStrategy.accepts(food);
    }

    /**
     * Adds the specified food to the shop.
     * @param food added food instance
     */
    @Override
    public void add(Food food) {
        if (discountStrategy.accepts(food)) {
            food.setDiscount(discount);
        }
        super.add(food);
    }
}
