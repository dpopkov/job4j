package ru.job4j.comparator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@SuppressWarnings("SpellCheckingInspection")
public class StringsCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov");
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftShorterThanRightResultShouldBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftLongerThanRightResultShouldBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanova",
                "Ivanov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}
