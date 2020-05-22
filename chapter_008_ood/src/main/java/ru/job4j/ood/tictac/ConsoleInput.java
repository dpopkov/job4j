package ru.job4j.ood.tictac;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Input that can be used on standard console.
 */
public class ConsoleInput implements Input {
    private final Output output;
    private final Scanner scanner;

    /** Constructs the input instance using the specified output and inputStream. */
    public ConsoleInput(Output output, InputStream inputStream) {
        this.output = output;
        scanner = new Scanner(inputStream);
    }

    /** Requests position for the next move using the specified prompt. */
    @Override
    public Position requestPosition(String prompt) {
        while (true) {
            try {
                output.print(prompt);
                String answer = scanner.nextLine();
                String[] tokens = answer.split("\\s+");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);
                return new Position(row, col);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                output.print("Incorrect position. Try again");
            }
        }
    }
}
