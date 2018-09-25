package ru.job4j.chess.figures.black;

import ru.job4j.chess.behaviors.NoMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class QueenBlack extends Figure {
    /**
     * Constructs QueenBlack with specified position.
     * @param position position on board
     */
    public QueenBlack(Cell position) {
        super(position, new NoMove());
    }
}
