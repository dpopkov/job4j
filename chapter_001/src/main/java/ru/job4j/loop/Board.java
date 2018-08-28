package ru.job4j.loop;

/**
 * Represents a chess board.
 *
 * @author Denis Popkov
 */
public class Board {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Creates a string representation of a bord with specified dimensions.
     * @param width width of the board (number of columns)
     * @param height height of the board (number of rows)
     * @return board as string of characters
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if ((row + col) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(LINE_SEPARATOR);
        }
        return screen.toString();
    }
}
