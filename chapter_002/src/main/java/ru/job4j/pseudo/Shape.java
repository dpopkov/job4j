package ru.job4j.pseudo;

/**
 * Declares method for drawing shapes.
 */
public interface Shape {
    /**
     * Line separator constant for new line.
     */
    String NEW_LINE = System.lineSeparator();

    /**
     * Returns a pseudo-graphic representation of a shape.
     * @return pseudo-graphic shape
     */
    String draw();
}
