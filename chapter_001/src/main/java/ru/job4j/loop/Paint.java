package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Contains methods of painting geometric shapes.
 *
 * @author Denis Popkov
 */
public class Paint {
    private static final String NEW_LINE = System.lineSeparator();

    /**
     * Creates a string representation of pyramid.
     * @param height height of the pyramid
     * @return pyramid as string of characters
     */
    public String pyramid(int height) {
        return this.loopBy(height, 2 * height - 1,
                (row, col) -> col >= height - 1 - row && col <= height - 1 + row);
    }

    /**
     * Creates a string representation of right triangle.
     * @param height height of the triangle
     * @return right triangle as string of characters
     */
    public String rightTrl(int height) {
        //noinspection SuspiciousNameCombination
        return this.loopBy(height, height,
                (row, col) -> col <= row);
    }

    /**
     * Creates a string representation of left triangle.
     * @param height height of the triangle
     * @return left triangle as string of characters
     */
    public String leftTrl(int height) {
        //noinspection SuspiciousNameCombination
        return this.loopBy(height, height,
                (row, col) -> col >= (height - row - 1));
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predicate) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (predicate.test(row, col)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(NEW_LINE);
        }
        return screen.toString();
    }
}
