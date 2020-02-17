package ru.job4j.ood.warehouse.quality;

import java.time.LocalDate;

/** Contains helper methods for testing purposes. */
public class TestHelpers {

    /** Creates stub instance of {@link Expirable} . */
    public static Expirable stubExpirable(int creationDay, int expirationDay) {
        return new Expirable() {
            @Override
            public LocalDate created() {
                return stubDate(creationDay);
            }

            @Override
            public LocalDate expires() {
                return stubDate(expirationDay);
            }
        };
    }

    /** Creates stub local date. */
    public static LocalDate stubDate(int day) {
        return LocalDate.of(2020, 1, day);
    }
}
