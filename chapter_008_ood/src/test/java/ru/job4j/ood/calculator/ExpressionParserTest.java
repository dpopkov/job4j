package ru.job4j.ood.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ExpressionParserTest {
    @Test
    public void whenParseOneNumberThenReturnsNumber() {
        ExpressionParser parser = new ExpressionParser();
        parser.parse("32.1");
        assertTrue(parser.hasFirstOperand());
        assertTrue(parser.hasSingleToken());
        assertThat(parser.firstOperand(), is(32.1));
    }

    @Test
    public void whenParseTwoOperandsThenReturnsBoth() {
        ExpressionParser parser = new ExpressionParser();
        parser.parse("3.0 + 2");
        assertTrue(parser.hasFirstOperand());
        assertFalse(parser.hasSingleToken());
        assertThat(parser.firstOperand(), is(3.0));
        assertThat(parser.operation(), is("+"));
        assertThat(parser.secondOperand(), is(2.0));
    }

    @Test
    public void whenParseOneOperandThenReturnsOne() {
        ExpressionParser parser = new ExpressionParser();
        parser.parse("* 42.3");
        assertFalse(parser.hasFirstOperand());
        assertFalse(parser.hasSingleToken());
        assertThat(parser.operation(), is("*"));
        assertThat(parser.secondOperand(), is(42.3));
    }
}
