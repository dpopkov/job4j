package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The class is responsible for the movement of chess figures.
 */
public class Logic {
    /**
     * Arrays of all figures involved in the game.
     */
    private final Figure[] figures = new Figure[32];
    /**
     * Index used when adding figures.
     */
    private int index = 0;

    /**
     * Adds figure.
     * @param figure figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Moves the figure from the specified source cell to the destination cell.
     * @param source source cell
     * @param dest destination cell
     * @return true if the movement was successful, false otherwise
     * @throws ImpossibleMoveException throws when the destination can not be reached in one move
     * @throws FigureNotFoundException throws when there is no figure in source cell
     * @throws OccupiedWayException throws when the route to destination is occupied
     */
    public boolean move(Cell source, Cell dest)
            throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException();
        }
        Cell[] steps = this.figures[index].way(source, dest);
        if (!routeIsFree(steps)) {
            throw new OccupiedWayException();
        }
        boolean rst = false;
        if (steps.length > 0) {
            this.figures[index] = this.figures[index].copy(dest);
            rst = true;
        }
        return rst;
    }

    /**
     * Removes all figures.
     */
    public void clean() {
        IntStream.range(0, this.figures.length).forEach(i -> this.figures[i] = null);
        this.index = 0;
    }

    /**
     * Checks whether all cells on the route are not occupied.
     * @param steps cells on the route including destination
     * @return true if the route is free, false otherwise
     */
    private boolean routeIsFree(Cell[] steps) {
        return Stream.of(steps).noneMatch(cell -> findBy(cell) != -1);
    }

    /**
     * Finds the index in the {@link Logic#figures} array of a figure which occupies the specified cell.
     * @param cell cell
     * @return index of -1 if cell is not occupied
     */
    private int findBy(Cell cell) {
        return IntStream.range(0, this.figures.length)
                .filter(index -> this.figures[index] != null && this.figures[index].position().equals(cell))
                .findFirst().orElse(-1);
    }
}
