package ru.job4j.ood.store.foods;

import ru.job4j.ood.store.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Represents milk. */
public class Milk extends Food {
    public Milk(LocalDate createDate, LocalDate expireDate, BigDecimal price) {
        super("Milk", createDate, expireDate, price, null);
    }
}
