package ru.job4j.ood.store;

import ru.job4j.ood.store.cycle.Expirable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Represents any kind of food that can be stored in a warehouse and sold in a shop. */
public abstract class Food implements Expirable {
    /** Name of the food. */
    private final String name;
    /** Date of creation. */
    private final LocalDate createDate;
    /** Date of expiration. */
    private final LocalDate expireDate;
    /** Price of the food. */
    private final BigDecimal price;
    /** Discount of the food. */
    private BigDecimal discount;

    /** Creates food instance and initializes it with the specified name, dates, price and discount.
     * @param name name of the food
     * @param createDate date of creation
     * @param expireDate date of expiration
     * @param price price of the food
     * @param discount discount of the food
     */
    public Food(String name, LocalDate createDate, LocalDate expireDate, BigDecimal price, BigDecimal discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    /** Returns name of the food. */
    public String getName() {
        return name;
    }

    /** Returns price of the food. */
    public BigDecimal getPrice() {
        return price;
    }

    /** Returns discount of the food. */
    public BigDecimal getDiscount() {
        return discount;
    }

    /** Returns date of creation. */
    @Override
    public LocalDate created() {
        return createDate;
    }

    /** Returns date of expiration. */
    @Override
    public LocalDate expires() {
        return expireDate;
    }

    /** Sets the specified discount. */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{name='" + name + "', createDate=" + createDate + ", expireDate=" + expireDate
                + ", price=" + price + ", discount=" + discount + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Food food = (Food) obj;
        return Objects.equals(name, food.name)
                && Objects.equals(createDate, food.createDate)
                && Objects.equals(expireDate, food.expireDate)
                && Objects.equals(price, food.price)
                && Objects.equals(discount, food.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expireDate, price, discount);
    }
}
