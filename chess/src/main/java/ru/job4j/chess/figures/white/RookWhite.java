package ru.job4j.chess.figures.white;

import ru.job4j.chess.behaviors.NoMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class RookWhite extends Figure {
    /**
     * Constructs RookWhite with specified position.
     * @param position position on board
     */
    public RookWhite(Cell position) {
        super(position, new NoMove());
    }
}
