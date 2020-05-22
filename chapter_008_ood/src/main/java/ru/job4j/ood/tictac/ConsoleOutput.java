package ru.job4j.ood.tictac;

import java.io.PrintStream;

/**
 * Output that can be used on standard console.
 */
public class ConsoleOutput implements Output {
    private final GridFormatter formatter;
    private final PrintStream printStream;

    /** Constructs the formatter instance using specified formatter and printStream. */
    public ConsoleOutput(GridFormatter formatter, PrintStream printStream) {
        this.formatter = formatter;
        this.printStream = printStream;
    }

    /** Prints the specified game grid. */
    @Override
    public void printGrid(GridView grid) {
        printStream.print(formatter.format(grid));
    }

    /** Prints the specified message. */
    @Override
    public void print(String message) {
        printStream.print(message);
    }

    /** Prints the specified message and newline symbol. */
    @Override
    public void println(String message) {
        printStream.println(message);
    }
}
