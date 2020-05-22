package ru.job4j.ood.tictac;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Configuration of the application that should be initialized with String arguments.
 */
public class ArgsConfig implements Config {
    private static final int DEFAULT_GRID_SIZE = 3;
    private static final int DEFAULT_WINNING_LINE_LENGTH = 3;

    private static final ArgsConfig INSTANCE = new ArgsConfig();

    public static ArgsConfig instance() {
        return INSTANCE;
    }

    /** Command line arguments. */
    private String[] args;
    /** Index of the current argument. */
    private int idx;
    private boolean initialized = false;
    private int gridSize = DEFAULT_GRID_SIZE;
    private PlayerType firstPlayer = PlayerType.HUMAN;
    private PlayerType secondPlayer = PlayerType.COMPUTER;
    private int winLineLength = DEFAULT_WINNING_LINE_LENGTH;

    /** Actions used to receive values of the arguments. */
    private final Map<String, Supplier<Object>> actions = Map.of(
            "-s", () -> gridSize = Integer.parseInt(nextArg()),
            "-p1", () -> firstPlayer = parsePlayerType(nextArg()),
            "-p2", () -> secondPlayer = parsePlayerType(nextArg()),
            "-w", () -> winLineLength = Integer.parseInt(nextArg())
    );

    private String nextArg() {
        return args[++idx];
    }

    private ArgsConfig() {
    }

    /** Initializes this config with the specified arguments. */
    public void init(String[] args) {
        this.args = args;
        parseArgs();
        initialized = true;
    }

    /** Returns size of the grid. */
    @Override
    public int getGridSize() {
        checkInitialized();
        return gridSize;
    }

    /** Returns type of the first player. */
    @Override
    public PlayerType getFirstPlayer() {
        checkInitialized();
        return firstPlayer;
    }

    @Override
    public PlayerType getSecondPlayer() {
        return secondPlayer;
    }

    /** Returns number of marks in line that wins the game. */
    @Override
    public int getWinLineLength() {
        checkInitialized();
        return winLineLength;
    }

    /** Produces parsed arguments. */
    private void parseArgs() {
        for (idx = 0; idx < args.length; idx++) {
            String arg = args[idx];
            actions.getOrDefault(arg, () -> {
                throw new IllegalArgumentException(String.format("Invalid argument: '%s'", arg));
            }).get();
        }
    }

    private void checkInitialized() {
        if (!initialized) {
            throw new IllegalStateException("This config instance is not initialized yet");
        }
    }

    private PlayerType parsePlayerType(String arg) {
        try {
            return PlayerType.valueOf(arg.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return PlayerType.COMPUTER;
        }
    }
}
