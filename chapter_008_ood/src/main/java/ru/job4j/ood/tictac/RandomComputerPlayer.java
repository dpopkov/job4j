package ru.job4j.ood.tictac;

import java.util.BitSet;

/**
 * Computer player that makes random moves.
 */
public class RandomComputerPlayer implements Player {
    private static final long DEFAULT_ANSWER_DELAY = 1000L;

    private final Mark mark;
    private final long delay;

    /** Constructs random player with the specified mark. */
    public RandomComputerPlayer(Mark mark) {
        this.mark = mark;
        delay = DEFAULT_ANSWER_DELAY;
    }

    /** Constructs random player for unit-testing purposes. */
    RandomComputerPlayer(Mark mark, long delay) {
        this.mark = mark;
        this.delay = delay;
    }

    /**
     * Returns position for the next move in the game using the specified grid view
     * or null if the grid is full.
     */
    @Override
    public Position makeMove(GridView view) {
        if (view.isFull()) {
            return null;
        }
        fakeThinkingPause();
        int count = 0;
        int numPositions = view.size() * view.size();
        BitSet triedIndexes = new BitSet(numPositions);
        Position pos = null;
        boolean searchingForFree = true;
        while (searchingForFree && count < numPositions) {
            int idx = (int) (Math.random() * numPositions);
            if (!triedIndexes.get(idx)) {
                triedIndexes.set(idx);
                count++;
                pos = new Position(idx / view.size(), idx % view.size());
                if (view.isFreeAt(pos)) {
                    searchingForFree = false;
                }
            }
        }
        return pos;
    }

    /** Returns mark used by this player. */
    @Override
    public Mark getMark() {
        return mark;
    }

    private void fakeThinkingPause() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
