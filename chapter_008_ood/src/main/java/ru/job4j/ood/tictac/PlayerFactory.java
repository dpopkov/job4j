package ru.job4j.ood.tictac;

/**
 * Factory that can create players for the game.
 */
public interface PlayerFactory {

    /** Creates player with the specified parameters. */
    Player create(Mark mark, PlayerType type, Input input);
}
