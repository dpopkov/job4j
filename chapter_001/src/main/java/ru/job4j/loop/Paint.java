package ru.job4j.loop;

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
        StringBuilder builder = new StringBuilder();
        int width = height * 2 - 1;
        int left, right;
        for (int row = 0; row < height; row++) {
            left = height - 1 - row;
            right = height - 1 + row;
            for (int col = 0; col < width; col++) {
                if (col >= left && col <= right) {
                    builder.append("^");
                } else {
                    builder.append(" ");
                }
            }
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }
}
