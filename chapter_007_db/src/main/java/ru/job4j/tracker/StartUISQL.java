package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * The entry point to the application that uses {@link TrackerSQL} as tracker.
 * Provides the main program cycle.
 */
public class StartUISQL extends StartUI {
    private static final String DB_NAME = "item_tracker";
    private static final String SCRIPT_NAME = "sql/createItemTracker.sql";

    private static final Logger LOG = LoggerFactory.getLogger(StartUISQL.class);

    /**
     * Constructs {@code StartUI} instance.
     * @param input   input system
     * @param tracker storage of items
     * @param console console used for printing messages
     */
    public StartUISQL(Input input, ITracker tracker, Consumer<String> console) {
        super(input, tracker, console);
    }

    /**
     * Main method.
     * @param args not used
     */
    public static void main(String[] args) {
        Consumer<String> consoleOutput = System.out::println;
        ValidateInput input = new ValidateInput(new ConsoleInput(), consoleOutput);
        Properties props = JdbcHelper.readProperties(JdbcHelper.PROPERTIES_FILENAME);
        JdbcHelper helper = new JdbcHelper(props);
        try (Connection connection = helper.connectToExistingDb(DB_NAME, SCRIPT_NAME)) {
            ITracker tracker = new TrackerSQL(connection);
            new StartUI(input, tracker, consoleOutput).init();
        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
