package ru.job4j.chess.figures.black;

import ru.job4j.chess.behaviors.OneCellForwardMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class PawnBlack extends Figure {
    public static final int BLACK_PAWN_DELTA_Y = -1;

    /**
     * Constructs PawnBlack with specified position.
     * @param position position on board
     */
    public PawnBlack(final Cell position) {
        super(position, new OneCellForwardMove(BLACK_PAWN_DELTA_Y));
    }
}
