package ru.job4j.ood.warehouse.foods;

import ru.job4j.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Represents bread. */
public class Bread extends Food {
    public Bread(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Bread", createDate, expireDate, price, null);
    }
}
