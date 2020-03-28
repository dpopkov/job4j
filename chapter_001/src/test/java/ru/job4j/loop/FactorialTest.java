package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FactorialTest {
    @Test
    public void when0Then1() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result, is(1));
    }

    @Test
    public void when5Then120() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }
}
