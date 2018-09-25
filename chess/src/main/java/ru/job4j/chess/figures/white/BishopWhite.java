package ru.job4j.chess.figures.white;

import ru.job4j.chess.behaviors.DiagonalMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class BishopWhite extends Figure {
    /**
     * Constructs BishopWhite with specified position.
     * @param position position on board
     */
    public BishopWhite(Cell position) {
        super(position, new DiagonalMove());
    }
}
