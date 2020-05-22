package ru.job4j.ood.tictac;

/**
 * The game cycle that shows state of the grid and swaps the players.
 */
public class GameCycle {
    private final GameGrid grid;
    private final Output output;
    private final Player first;
    private final Player second;
    private final int winningLength;
    private Player currentPlayer;
    private Mark winner;

    /**
     * Constructs and initializes the game cycle.
     * @param grid game grid
     * @param output output that prints the state of game
     * @param first first player
     * @param second second player
     * @param winningLength number of adjacent marks needed to win the game
     */
    public GameCycle(GameGrid grid, Output output, Player first, Player second, int winningLength) {
        this.grid = grid;
        this.output = output;
        this.first = first;
        this.second = second;
        this.winningLength = winningLength;
        currentPlayer = first;
    }

    /** Starts the game cycle. */
    public void start() {
        output.printGrid(grid);
        while (stillPlaying()) {
            Position move = currentPlayer.makeMove(grid);
            if (move == null) {
                throw new IllegalStateException("Current player could not find a free cell");
            }
            if (!grid.isFreeAt(move)) {
                output.print("This cell is busy. ");
            } else {
                grid.setMark(move, currentPlayer.getMark());
                output.printGrid(grid);
                swapPlayers();
                winner = grid.getWinner(winningLength);
            }
        }
    }

    /** Returns the winning mark or null if there is no winner. */
    public Mark getWinner() {
        return winner;
    }

    private boolean stillPlaying() {
        return winner == null && !grid.isFull();
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == first ? second : first;
    }
}
