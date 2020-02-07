package ru.job4j.ood.calculator;

/** Represents name of a function. */
public enum FunctionName {
    SIN,
    COS,
    TAN;

    /** Transforms string representation to function name object. */
    public static FunctionName from(String s) {
        return FunctionName.valueOf(s.toUpperCase());
    }
}
