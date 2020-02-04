package ru.job4j.ood.calculator;

import java.util.Locale;
import java.util.Scanner;

/**
 * Parses simple arithmetic expressions in infix notation containing one operation.
 */
public class ExpressionParser {
    /** Flags that the expression has first operand. */
    private boolean hasFirst;
    /** Flags that the expression has single token only. */
    private boolean singleToken;
    /** First operand. */
    private double first;
    /** Arithmetic operation. */
    private String operation;
    /** Second operand. */
    private double second;

    /** Returns true if the parsed expression has first operand. */
    public boolean hasFirstOperand() {
        return hasFirst;
    }

    /** Returns true if the parsed expression has single token only. */
    public boolean hasSingleToken() {
        return singleToken;
    }

    /** Returns first operand. */
    public double firstOperand() {
        return first;
    }

    /** Returns second operand. */
    public double secondOperand() {
        return second;
    }

    /** Returns operation. */
    public String operation() {
        return operation;
    }

    /**
     * Parses the specified expression.
     * @param expression expression that should contain both operands and operation
     *                   or operation and the second operand
     */
    public void parse(String expression) {
        Scanner scanner = new Scanner(expression);
        scanner.useLocale(Locale.US);
        if (scanner.hasNextDouble()) {
            hasFirst = true;
            first = scanner.nextDouble();
        } else {
            hasFirst = false;
        }
        if (scanner.hasNext()) {
            operation = scanner.next();
            second = scanner.nextDouble();
        } else {
            singleToken = true;
        }
    }
}
