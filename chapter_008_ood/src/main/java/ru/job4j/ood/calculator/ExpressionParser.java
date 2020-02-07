package ru.job4j.ood.calculator;

/** Parser of string expressions. */
public interface ExpressionParser {

    /** Parses the specified string expression to {@link Expression} object. */
    Expression parse(String expression);
}
