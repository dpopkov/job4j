package ru.job4j.chess.figures.black;

import ru.job4j.chess.behaviors.NoMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class KingBlack extends Figure {
    /**
     * Constructs KingBlack with specified position.
     * @param position position on board
     */
    public KingBlack(Cell position) {
        super(position, new NoMove());
    }
}
