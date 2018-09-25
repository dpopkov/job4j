package ru.job4j.chess.figures.black;

import ru.job4j.chess.behaviors.NoMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class KnightBlack extends Figure {
    /**
     * Constructs KnightBlack with specified position.
     * @param position position on board
     */
    public KnightBlack(Cell position) {
        super(position, new NoMove());
    }
}
