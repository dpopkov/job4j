package ru.job4j.ood.warehouse.quality;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * This calculator can be used for determining state of an expirable entity.
 * It uses expiry date percentage for state calculation.
 */
public class SimpleStoreCycleCalculator implements StoreCycleCalculator {
    /** Top percent for store. */
    private final int topForStore;
    /** Top percent for sale. */
    private final int topForSale;
    /** The current date of calculation. */
    private final LocalDate today;

    /** Constructs and initializes the calculator with the specified expire date percentage.
     * @param topForStore top percent for store
     * @param topForSale top percent for sale
     * @param today current date of calculation
     */
    public SimpleStoreCycleCalculator(int topForStore, int topForSale, LocalDate today) {
        this.topForStore = topForStore;
        this.topForSale = topForSale;
        this.today = today;
    }

    /**
     * Calculates the store cycle state of the specified entity.
     * @param expirable expirable entity
     * @return state of entity in the accepted storage cycle
     */
    public StoreCycle calculate(Expirable expirable) {
        LocalDate created = expirable.created();
        LocalDate expires = expirable.expires();
        long totalDays = created.until(expires, ChronoUnit.DAYS);
        long daysToToday = created.until(today, ChronoUnit.DAYS);
        double percent = (double) daysToToday / totalDays * 100;
        if (percent < topForStore) {
            return StoreCycle.STORAGE;
        } else if (percent < topForSale) {
            return StoreCycle.FOR_SALE;
        } else if (percent <= 100) {
            return StoreCycle.DISCOUNT_SALE;
        } else {
            return StoreCycle.EXPIRED;
        }
    }
}
