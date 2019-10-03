package ru.job4j.vacparser.parsers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DateTimeParserTest {

    @Test
    public void whenParseDateWithMonthThenReturnsCorrectResult() {
        String input = "3 май 19, 19:53";
        LocalDateTime result = new DateTimeParser().parse(input);
        LocalDateTime expected = LocalDateTime.of(2019, 5, 3, 19, 53, 0);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParseDateOfTodayThenReturnsToday() {
        String input = "сегодня, 09:03";
        LocalDateTime result = new DateTimeParser().parse(input);
        LocalTime time = LocalTime.of(9, 3);
        assertThat(result, is(LocalDateTime.of(LocalDate.now(), time)));
    }

    @Test
    public void whenParseDateOfYesterdayThenReturnsYesterday() {
        String input = "вчера, 09:03";
        LocalDateTime result = new DateTimeParser().parse(input);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalTime time = LocalTime.of(9, 3);
        assertThat(result, is(LocalDateTime.of(yesterday, time)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParseInvalidDateThenException() {
        String input = "---, 09:03";
        new DateTimeParser().parse(input);
    }
}
