package ru.job4j.ood.warehouse.quality;

import java.math.BigDecimal;

/**
 * Represents entity that can change discount.
 */
public interface Discountable {
    /**
     * Sets the specified discount value.
     */
    void setDiscount(BigDecimal discount);
}
