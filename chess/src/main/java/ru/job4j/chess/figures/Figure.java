package ru.job4j.chess.figures;

import ru.job4j.chess.behaviors.MovingBehavior;

/**
 * Base class from which all concrete figures must inherit.
 */
public abstract class Figure {
    /**
     * Current position on chess board.
     */
    private final Cell position;
    /**
     * Algorithm which implements moving behavior of concrete type of figure.
     */
    private MovingBehavior movingBehavior;

    /**
     * Initializes figure with position and moving behavior.
     * @param position position on board
     * @param movingBehavior moving behavior of concrete type of figure
     */
    public Figure(final Cell position, MovingBehavior movingBehavior) {
        this.position = position;
        this.movingBehavior = movingBehavior;
    }

    public MovingBehavior getMovingBehavior() {
        return movingBehavior;
    }

    /**
     * Current position on chess board.
     * @return current position
     */
    public Cell position() {
        return this.position;
    }

    /**
     * Checks that this figure can move this way and returns cells included in this way.
     * @param source source position
     * @param dest destination position
     * @return array of cells that the figure must pass through
     */
    public Cell[] way(Cell source, Cell dest) {
        return movingBehavior.way(source, dest);
    }

    /**
     * Name of the figure's icon file.
     * @return icon file name
     */
    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    /**
     * Creates copy of the figure on destination position.
     * @param dest destination cell
     * @return copy of the figure
     */
    public Figure copy(Cell dest) {
        try {
            Figure copied = this.getClass().getConstructor(Cell.class).newInstance(dest);
            copied.movingBehavior = this.movingBehavior;
            return copied;
        } catch (Exception e) {
            throw new RuntimeException("Error copying figure", e);
        }
    }
}
