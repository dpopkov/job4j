package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

/**
 * Implements game logic.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    /**
     * Size of the square board.
     */
    private final int size;
    /**
     * All figures filling the board.
     */
    private final Figure[] figures;
    /**
     * Index of next free slot in {@code figures}.
     */
    private int index = 0;

    /**
     * Constructs instance of {@code Logic} for the board of specified size.
     * @param size size of the board
     */
    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    /**
     * Adds new figure.
     * @param figure figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Moves a figure from specified source cell to destination cell.
     * The movement is implemented by creating a new figure on destination cell.
     * @param source source cell
     * @param dest destination cell
     * @return true if this movement was carried out, false otherwise
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                this.figures[index] = this.figures[index].copy(dest);
                rst = true;
            }
        }
        return rst;
    }

    /**
     * Checks if the way consisting of the specified cells exist.
     * @param cells checked cells
     * @return true if all cells exist, false otherwise
     */
    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    /**
     * Removes all figures from logic.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Finds index of the specified cell.
     * @param cell cell to find
     * @return index of the cell, or -1 if not found
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Checks if the current game wins.
     * @return true if wins, false otherwise
     */
    public boolean isWin() {
        int[][] table = this.convert();
        return anyColumnWins(table) || anyRowWins(table);
    }

    /**
     * Checks if any row in the specified table wins.
     * @param table table
     * @return true if wins, false otherwise
     */
    private boolean anyRowWins(int[][] table) {
        boolean winner = false;
        for (int row = 0; !winner && row < this.size; row++) {
            winner = lineWins(table, row, 0, Direction.RIGHT);
        }
        return winner;
    }

    /**
     * Checks if any column in the specified table wins.
     * @param table table
     * @return true if wins, false otherwise
     */
    private boolean anyColumnWins(int[][] table) {
        boolean winner = false;
        for (int col = 0; !winner && col < this.size; col++) {
            winner = lineWins(table, 0, col, Direction.DOWN);
        }
        return winner;
    }

    /**
     * Represents possible moving directions when passing through two-dimensional table.
     */
    private enum Direction {
        RIGHT(1, 0), DOWN(0, 1);

        private final int deltaX;
        private final int deltaY;

        Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }

    /**
     * Checks if the line formed by cells starting from specified row and column wins.
     * @param table table
     * @param startRow starting row
     * @param startCol starting column
     * @param direction direction when moving along the line
     * @return true if line wins, false otherwise
     */
    private boolean lineWins(int[][] table, int startRow, int startCol, Direction direction) {
        boolean result = true;
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < this.size; i++) {
            if (table[row][col] != 1) {
                result = false;
                break;
            }
            row += direction.deltaY;
            col += direction.deltaX;
        }
        return result;
    }

    /**
     * Returns two-dimensional array of numbers where 1 represents a movable figure.
     * @return array of 1 and 0
     */
    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }
}
