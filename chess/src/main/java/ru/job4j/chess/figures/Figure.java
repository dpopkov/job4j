package ru.job4j.chess.figures;

public interface Figure {
    /**
     * Current position on chess board.
     * @return current position
     */
    Cell position();

    /**
     * Checks that this figure can move this way and returns cells included in this way.
     * @param source source position
     * @param dest destination position
     * @return array of cells that the figure must pass through
     */
    Cell[] way(Cell source, Cell dest);

    /**
     * Name of the figure's icon file.
     * @return icon file name
     */
    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    /**
     * Creates copy of the figure on destination position.
     * @param dest destination cell
     * @return copy of the figure
     */
    Figure copy(Cell dest);
}
