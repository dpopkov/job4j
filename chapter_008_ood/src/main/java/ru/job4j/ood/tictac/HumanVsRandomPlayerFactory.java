package ru.job4j.ood.tictac;

/**
 * Factory that can create a human or random computer player.
 */
public class HumanVsRandomPlayerFactory implements PlayerFactory {

    /** Creates player with the specified parameters. */
    @Override
    public Player create(Mark mark, PlayerType type, Input input) {
        if (type == PlayerType.HUMAN) {
            return new HumanPlayer(mark, input);
        } else if (type == PlayerType.COMPUTER) {
            return new RandomComputerPlayer(mark);
        }
        throw new IllegalArgumentException("Invalid player type: " + type);
    }
}
