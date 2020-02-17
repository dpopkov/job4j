package ru.job4j.ood.warehouse.quality;

import java.time.LocalDate;

/** Represents entity with dates of creation and expiration. */
public interface Expirable {
    /** Returns date of creation. */
    LocalDate created();

    /** Returns date of expiration. */
    LocalDate expires();
}
