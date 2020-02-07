package ru.job4j.ood.calculator;

import java.util.Map;

/** Represents arithmetic operation. */
public enum ArithmeticOperation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    /** Map of symbols to operations. */
    private static final Map<String, ArithmeticOperation> OPERATIONS = Map.of(
            "+", ADD,
            "-", SUBTRACT,
            "*", MULTIPLY,
            "/", DIVIDE
    );

    /** Transforms string representation to operation object. */
    public static ArithmeticOperation from(String s) {
        ArithmeticOperation operation = OPERATIONS.get(s);
        if (operation == null) {
            throw new IllegalArgumentException("Can not infer any arithmetic operation from this argument: " + s);
        }
        return operation;
    }
}
