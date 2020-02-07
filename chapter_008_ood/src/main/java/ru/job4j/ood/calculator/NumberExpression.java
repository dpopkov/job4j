package ru.job4j.ood.calculator;

import java.util.Objects;

/** Wraps a number as an expression that can be evaluated. */
public class NumberExpression implements Expression {
    private final double value;

    /** Initializes number expression with specified number. */
    public NumberExpression(double value) {
        this.value = value;
    }

    /** Returns value of the number. */
    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumberExpression that = (NumberExpression) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "NumberExpression{value=" + value + '}';
    }
}
