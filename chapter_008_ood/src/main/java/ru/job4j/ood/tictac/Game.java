package ru.job4j.ood.tictac;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Tic-tac-toe game that uses pseudo text console output.
 * Starts the game cycle and shows the winner.
 */
public class Game {
    private final Output output;
    private final Input input;
    private final GameCycle cycle;
    private final PlayerFactory playerFactory;

    /** Constructs the game instance using the specified config, player factory, and I/O streams. */
    public Game(Config config, PlayerFactory factory, PrintStream out, InputStream in) {
        output = new ConsoleOutput(new PseudoTextGridFormatter(), out);
        input = new ConsoleInput(output, in);
        playerFactory = factory;
        cycle = initCycle(config);
    }

    /** Runs the game and displays the outcome. */
    public void run() {
        cycle.start();
        Mark winner = cycle.getWinner();
        if (winner != null) {
            output.println(winner + " is the winner");
        } else {
            output.println("The game ended in a draw");
        }
    }

    private GameCycle initCycle(Config config) {
        GameGrid grid = new ArrayGrid(config.getGridSize());
        Player first = playerFactory.create(Mark.X, config.getFirstPlayer(), input);
        Player second = playerFactory.create(Mark.O, config.getSecondPlayer(), input);
        return new GameCycle(grid, output, first, second, config.getWinLineLength());
    }
}
