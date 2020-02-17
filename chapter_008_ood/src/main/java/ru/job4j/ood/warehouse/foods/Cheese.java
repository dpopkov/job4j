package ru.job4j.ood.warehouse.foods;

import ru.job4j.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Represents cheese. */
public class Cheese extends Food {
    public Cheese(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Cheese", createDate, expireDate, price, null);
    }
}
