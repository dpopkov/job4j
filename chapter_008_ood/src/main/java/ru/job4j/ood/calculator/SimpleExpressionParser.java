package ru.job4j.ood.calculator;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses simple arithmetic expressions in infix notation containing one operation.
 */
public class SimpleExpressionParser implements ExpressionParser {
    private static final Pattern FUNCTION_PATTERN = Pattern.compile("([a-zA-Z]+)\\((\\d+(\\.\\d+)?)\\)");
    /** Calculator used for arithmetic operations. */
    private final ArithmeticCalculator arithmeticCalculator;
    /** Calculator used for functions. */
    private final FunctionCalculator functionCalculator;
    /** Stores parsed function name. */
    private String functionName;
    /** Stores parsed function argument. */
    private String functionArgument;

    /**
     * Initializes calculator with the specified arithmetic and function calculators.
     * @param arithmeticCalculator calculator used for arithmetic operations
     * @param functionCalculator calculator used for functions
     */
    public SimpleExpressionParser(ArithmeticCalculator arithmeticCalculator, FunctionCalculator functionCalculator) {
        this.arithmeticCalculator = arithmeticCalculator;
        this.functionCalculator = functionCalculator;
    }

    /**
     * Parses the specified expression.
     * @param expression expression that should contain both operands and operation,
     *                   or operation and the second operand,
     *                   or function with one argument
     * @return the parsed expression as object
     */
    @Override
    public Expression parse(String expression) {
        Scanner scanner = new Scanner(expression);
        scanner.useLocale(Locale.US);
        double first = -1;
        if (scanner.hasNextDouble()) {
            first = scanner.nextDouble();
            if (!scanner.hasNext()) {
                return new NumberExpression(first);
            }
        }
        if (scanner.hasNext()) {
            String operation = scanner.next();
            if (isFunction(operation)) {
                first = Double.parseDouble(functionArgument);
                return new FunctionExpression(
                        new NumberExpression(first),
                        FunctionName.from(functionName),
                        functionCalculator);
            } else {
                double second = scanner.nextDouble();
                return new ArithmeticExpression(
                        new NumberExpression(first),
                        new NumberExpression(second),
                        ArithmeticOperation.from(operation),
                        arithmeticCalculator);
            }
        }
        throw new IllegalStateException("Can not parse this expression properly: " + expression);
    }

    private boolean isFunction(String operation) {
        Matcher matcher = FUNCTION_PATTERN.matcher(operation);
        boolean matches = matcher.matches();
        if (matches) {
            functionName = matcher.group(1);
            functionArgument = matcher.group(2);
        }
        return matches;
    }
}
