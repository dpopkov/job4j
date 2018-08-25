package ru.job4j.calculate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculateTest {
    @Test
    public void whenOnePlusOneThenTwo() {
        Calculate calc = new Calculate();
        calc.add(1.0, 1.0);
        double result = calc.getResult();
        double expected = 2.0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeMinusOneThenTwo() {
        Calculate calc = new Calculate();
        calc.subtract(3.0, 1.0);
        double result = calc.getResult();
        double expected = 2.0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSixDividedByTwoThenThree() {
        Calculate calc = new Calculate();
        calc.div(6.0, 2.0);
        double result = calc.getResult();
        double expected = 3.0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeMultiplyByTwoThenSix() {
        Calculate calc = new Calculate();
        calc.multiply(3.0, 2.0);
        double result = calc.getResult();
        double expected = 6.0;
        assertThat(result, is(expected));
    }
}
