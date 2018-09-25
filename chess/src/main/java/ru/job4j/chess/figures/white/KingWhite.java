package ru.job4j.chess.figures.white;

import ru.job4j.chess.behaviors.NoMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class KingWhite extends Figure {
    /**
     * Constructs KingWhite with specified position.
     * @param position position on board
     */
    public KingWhite(Cell position) {
        super(position, new NoMove());
    }
}
