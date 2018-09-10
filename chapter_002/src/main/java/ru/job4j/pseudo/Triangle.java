package ru.job4j.pseudo;

/**
 * Triangle shape.
 */
public class Triangle implements Shape {
    /**
     * Height of the triangle.
     */
    private final int height;

    /**
     * Constructs instance of {@code Triangle} of specified height.
     * @param height height of the triangle
     */
    public Triangle(int height) {
        this.height = height;
    }

    /**
     * Returns a pseudo-graphic representation of the triangle.
     * @return pseudo-graphic triangle
     */
    @Override
    public String draw() {
        StringBuilder builder = new StringBuilder();
        int width = height * 2 - 1;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                builder.append(col >= height - 1 - row && col <= height - 1 + row ? "*" : " ");
            }
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }
}
