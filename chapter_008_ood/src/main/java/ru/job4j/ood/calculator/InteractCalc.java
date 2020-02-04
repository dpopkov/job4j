package ru.job4j.ood.calculator;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interactive text input driven calculator.
 * It can solves a simple arithmetic expression, e.g 3 + 2.
 * or an expression using the previous result, e.g. Result + 42
 * Uses command 'exit' to quit.
 */
public class InteractCalc {
    /** String expression evaluator. */
    private final ExpressionEvaluator evaluator;
    /** Sources of string input. */
    private Supplier<String> input;
    /** Destination of output. */
    private final Consumer<String> output;
    /** Flags the first expression in a sequence of user inputs. */
    private boolean firstExpression = true;
    /** Current result of calculation. */
    private double result;

    /** Constructs the calculator using the specified evaluator and output consumer.
     * The source of input should be initialized by {@link #setInput(Supplier)} method.
     * @param evaluator string expression evaluator
     * @param output destination of output
     */
    public InteractCalc(ExpressionEvaluator evaluator, Consumer<String> output) {
        this.evaluator = evaluator;
        this.output = output;
    }

    /**
     * Constructs the calculator using the specified evaluator, input producer, and output consumer
     * @param evaluator string expression evaluator
     * @param input source of input
     * @param output destination of output
     */
    public InteractCalc(ExpressionEvaluator evaluator, Supplier<String> input, Consumer<String> output) {
        this.evaluator = evaluator;
        this.input = input;
        this.output = output;
    }

    /** Sets the source of string input. */
    public void setInput(Supplier<String> input) {
        this.input = input;
    }

    /**
     * Starts the loop of interaction.
     */
    public void start() {
        output.accept("Enter expression: ");
        while (true) {
            String expression = input.get();
            if ("exit".equals(expression)) {
                break;
            }
            if (firstExpression) {
                result = evaluator.evaluate(expression);
                firstExpression = false;
            } else {
                result = evaluator.evaluate(result, expression);
            }
            output.accept(result + " ");
        }
    }
}
