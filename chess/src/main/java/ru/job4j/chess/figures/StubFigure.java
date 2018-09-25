package ru.job4j.chess.figures;

import ru.job4j.chess.behaviors.MovingBehavior;

/**
 * Figure for testing purposes
 */
public class StubFigure extends Figure {
    /**
     * Initializes figure with position and moving behavior.
     * @param position       position on board
     * @param movingBehavior moving behavior of concrete type of figure
     */
    public StubFigure(Cell position, MovingBehavior movingBehavior) {
        super(position, movingBehavior);
    }

    @Override
    public Figure copy(Cell dest) {
        return new StubFigure(dest, this.getMovingBehavior());
    }
}
