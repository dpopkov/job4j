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

    /**
     * Creates a string representation of right triangle.
     * @param height height of the triangle
     * @return right triangle as string of characters
     */
    public String rightTrl(int height) {
        StringBuilder builder = new StringBuilder();
        int width = height;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col <= row) {
                    builder.append("^");
                } else {
                    builder.append(" ");
                }
            }
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }

    /**
     * Creates a string representation of left triangle.
     * @param height height of the triangle
     * @return left triangle as string of characters
     */
    public String leftTrl(int height) {
        StringBuilder builder = new StringBuilder();
        int width = height;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col >= (height - row - 1)) {
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
