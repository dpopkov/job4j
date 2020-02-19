package ru.job4j.ood.store.foods;

import ru.job4j.ood.store.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Represents meat. */
public class Meat extends Food {
    public Meat(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Meat", createDate, expireDate, price, null);
    }
}
