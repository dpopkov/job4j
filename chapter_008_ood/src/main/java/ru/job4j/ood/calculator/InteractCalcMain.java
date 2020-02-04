package ru.job4j.ood.calculator;

import ru.job4j.calculate.Calculate;

import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Main class that starts the calculator application.<br>
 *
 * Example of interaction:<br>
 * Enter expression: <kbd>3 + 2</kbd><br>
 * 5.0 <kbd>/ 2</kbd><br>
 * 2.5 <kbd>* 3</kbd><br>
 * 7.5 <kbd>- 0.5</kbd><br>
 * 7.0 <kbd>exit</kbd>
 */
public class InteractCalcMain {
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(
                new ExpressionEvaluator(new Calculate(), new ExpressionParser()),
                new Supplier<>() {
                    private final Scanner in = new Scanner(System.in);

                    @Override
                    public String get() {
                        return in.nextLine();
                    }
                },
                System.out::print);
        calc.start();
    }
}
