package ru.job4j.pseudo;

/**
 * Contains method for printing pseudo-graphic shapes.
 */
public class Paint {
    /**
     * Prints shape in standard console.
     * @param shape shape
     */
    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }
}
