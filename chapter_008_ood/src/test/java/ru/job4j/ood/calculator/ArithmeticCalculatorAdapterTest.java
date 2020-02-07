package ru.job4j.ood.calculator;

import org.junit.Test;
import ru.job4j.calculate.Calculate;

import static org.junit.Assert.assertEquals;
import static ru.job4j.ood.calculator.ArithmeticOperation.*;

public class ArithmeticCalculatorAdapterTest {

    public static final double DELTA = 1e-15;

    @Test
    public void whenCalculateThenUsesCalculator() {
        ArithmeticCalculatorAdapter adapter = new ArithmeticCalculatorAdapter(new Calculate());
        assertEquals(6.0, adapter.calculate(3.0, MULTIPLY, 2.0), DELTA);
        assertEquals(1.5, adapter.calculate(3.0, DIVIDE, 2.0), DELTA);
        assertEquals(5.0, adapter.calculate(3.0, ADD, 2.0), DELTA);
        assertEquals(1.0, adapter.calculate(3.0, SUBTRACT, 2.0), DELTA);
    }
}
