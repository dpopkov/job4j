package ru.job4j.functional;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class CalculatorTest {
    private static final double DELTA = 1e-15;

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = calculator.diapason(-1, 2, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(-1D, 1D, 3D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquaringNumbersThenResultContainsSquares() {
        List<Double> result = calculator.diapason(0, 3, x -> x * x);
        List<Double> expected = Arrays.asList(0D, 1D, 4D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        List<Double> result = calculator.diapason(1, 4, Math::log);
        assertThat(result.get(0), closeTo(0, DELTA));
        assertThat(result.get(1), closeTo(0.693147180559945, DELTA));
        assertThat(result.get(2), closeTo(1.09861228866811, DELTA));
    }
}
