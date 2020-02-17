package ru.job4j.ood.warehouse.foods;

import ru.job4j.ood.warehouse.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Represents meat. */
public class Meat extends Food {
    public Meat(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Meat", createDate, expireDate, price, null);
    }
}
