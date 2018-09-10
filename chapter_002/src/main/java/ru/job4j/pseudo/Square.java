package ru.job4j.pseudo;

/**
 * Square shape.
 */
public class Square implements Shape {
    /**
     * Size of square.
     */
    private final int size;

    /**
     * Constructs instance of {@code Square} with specified size.
     * @param size size of the square
     */
    public Square(int size) {
        this.size = size;
    }

    /**
     * Returns a pseudo-graphic representation of the square.
     * @return pseudo-graphic square
     */
    @Override
    public String draw() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        int limit = size * size;
        while (i < limit) {
            builder.append("*");
            i++;
            if (i % size == 0) {
                builder.append(NEW_LINE);
            }
        }
        return builder.toString();
    }
}
