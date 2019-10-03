package ru.job4j.vacparser.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Parser of date time input.
 * Examples of allowed formats:<br>
 * 3 май 19, 19:53<br>
 * сегодня, 09:03<br>
 * вчера, 09:03
 */
public class DateTimeParser {
    public static final List<String> MONTHS = List.of(
            "янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"
    );
    private static final int YEAR_PREFIX = LocalDate.now().getYear() / 100;

    /**
     * Parses the specified date time input.
     * @param dateTime date time as in the examples in this {@link DateTimeParser} class comment
     * @return date time without time zone
     */
    public LocalDateTime parse(String dateTime) {
        String[] tokens = dateTime.split(", ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Illegal date time format: " + dateTime);
        }
        LocalDate date = parseDate(tokens[0]);
        LocalTime time = LocalTime.parse(tokens[1]);
        return LocalDateTime.of(date, time);
    }

    /** Parses date input. */
    private LocalDate parseDate(String dateString) {
        String[] tokens = dateString.split(" ");
        if (tokens.length == 3) {
            int day = Integer.parseInt(tokens[0]);
            int month = parseMonth(tokens[1]);
            int year = Integer.parseInt(tokens[2]);
            year = YEAR_PREFIX * 100 + year;
            return LocalDate.of(year, month, day);
        } else if (tokens.length == 1) {
            if ("вчера".equals(tokens[0])) {
                return LocalDate.now().minusDays(1);
            } else if ("сегодня".equals(tokens[0])) {
                return LocalDate.now();
            } else {
                throw new IllegalArgumentException("Illegal date format: " + dateString);
            }
        }
        throw new IllegalArgumentException("Illegal date format: " + dateString);
    }

    /** Parses month input. The month must be one of the strings in {@link #MONTHS} array. */
    private int parseMonth(String month) {
        int idx = MONTHS.indexOf(month);
        if (idx == -1) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        return idx + 1;
    }
}
