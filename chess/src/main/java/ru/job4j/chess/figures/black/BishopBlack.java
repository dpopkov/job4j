package ru.job4j.chess.figures.black;

import ru.job4j.chess.behaviors.DiagonalMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class BishopBlack extends Figure {
    /**
     * Constructs BishopBlack with specified position.
     * @param position position on board
     */
    public BishopBlack(Cell position) {
        super(position, new DiagonalMove());
    }
}
