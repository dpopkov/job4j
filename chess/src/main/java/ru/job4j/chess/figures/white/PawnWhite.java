package ru.job4j.chess.figures.white;

import ru.job4j.chess.behaviors.OneCellForwardMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class PawnWhite extends Figure {
    public static final int WHITE_PAWN_DELTA_Y = 1;

    /**
     * Constructs PawnWhite with specified position.
     * @param position position on board
     */
    public PawnWhite(final Cell position) {
        super(position, new OneCellForwardMove(WHITE_PAWN_DELTA_Y));
    }
}
