package ru.job4j.chess.figures.white;

import ru.job4j.chess.behaviors.NoMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class QueenWhite extends Figure {
    /**
     * Constructs QueenWhite with specified position.
     * @param position position on board
     */
    public QueenWhite(Cell position) {
        super(position, new NoMove());
    }
}
