package ru.job4j.ood.tictac;

/**
 * Main class that starts the game.<br>
 * It can use command line arguments:<br>
 * <pre>
 * -s sizeOfGrid (default value is 3)
 * -p1 firstPlayer ('human' or 'computer')
 * -p2 secondPlayer ('computer' or 'human')
 * -w winningLineLength (default value is 3)
 * </pre>
 * For example, these arguments allow human play vs computer on 3 x 3 grid:<br>
 * -s 3 -p1 human -p2 computer<br>
 * and these arguments allow computer play vs computer on 5 x 5 grid:<br>
 * -s 5 -p1 computer -p2 computer
 */
public class TicTacToeMain {
    public static void main(String[] args) {
        ArgsConfig config = ArgsConfig.instance();
        config.init(args);
        Game game = new Game(config, new HumanVsRandomPlayerFactory(), System.out, System.in);
        game.run();
    }
}
