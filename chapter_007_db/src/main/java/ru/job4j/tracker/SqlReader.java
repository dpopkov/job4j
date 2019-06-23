package ru.job4j.tracker;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Helps to read sql statements.
 */
public class SqlReader {
    private static final String SQL_STATEMENT_SEPARATOR = ";\\s*";

    /**
     * Reads sql statements that are separated by a semicolon.
     * @param in input stream
     * @return list of strings where each string represents a separate sql statement
     */
    public List<String> readSqlStatements(InputStream in) {
        List<String> statements = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedInputStream(in))) {
            scanner.useDelimiter(SQL_STATEMENT_SEPARATOR);
            while (scanner.hasNext()) {
                statements.add(scanner.next());
            }
            return statements;
        }
    }
}
