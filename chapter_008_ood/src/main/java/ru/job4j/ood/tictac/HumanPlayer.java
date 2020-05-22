package ru.job4j.ood.tictac;

/**
 * Helper used for getting input from playing human.
 */
public class HumanPlayer implements Player {
    private final Mark mark;
    private final Input input;

    /** Constructs the player using the specified mark and input. */
    public HumanPlayer(Mark mark, Input input) {
        this.mark = mark;
        this.input = input;
    }

    /** Make the next move in the game using the specified grid view. */
    @Override
    public Position makeMove(GridView view) {
        return input.requestPosition("Enter row and column for next " + mark + ": ");
    }

    /** Returns mark used by this player. */
    @Override
    public Mark getMark() {
        return mark;
    }
}
