package ru.job4j.ood.tictac;

/**
 * Formatter of grid that uses pseudo-symbols for displaying grid lines.
 */
public class PseudoTextGridFormatter implements GridFormatter {
    private final static String NEW_LINE = System.lineSeparator();
    private final static String HOR_LINE = "\u2500\u2500\u2500";
    private final static char VERT_LINE = '\u2502';
    private final static char TOP_LEFT_CORNER = '\u250C';
    private final static char TOP_RIGHT_CORNER = '\u2510';
    private final static char BOTTOM_LEFT_CORNER = '\u2514';
    private final static char BOTTOM_RIGHT_CORNER = '\u2518';
    private final static char LEFT_TEE = '\u251C';
    private final static char RIGHT_TEE = '\u2524';
    private final static char TOP_TEE = '\u252C';
    private final static char BOTTOM_TEE = '\u2534';
    private final static char CROSS = '\u253C';

    /** Formats the specified grid into String. */
    @Override
    public String format(GridView grid) {
        StringBuilder buffer = new StringBuilder();
        int size = grid.size();
        makeTop(buffer, size);
        for (int r = 0; r < size; r++) {
            makeMarkRow(buffer, r, grid);
            if (r < size - 1) {
                makeHorizontal(buffer, size);
            }
        }
        makeBottom(buffer, size);
        return buffer.toString();
    }

    private void makeMarkRow(StringBuilder buffer, int row, GridView grid) {
        int size = grid.size();
        buffer.append(VERT_LINE);
        for (int c = 0; c < size; c++) {
            buffer.append(" ");
            buffer.append(grid.markAt(new Position(row, c)));
            buffer.append(" ");
            buffer.append(VERT_LINE);
        }
        buffer.append(NEW_LINE);
    }

    private void makeHorizontal(StringBuilder buffer, int size) {
        makeGraphRow(buffer, size, LEFT_TEE, CROSS, RIGHT_TEE);
    }

    private void makeBottom(StringBuilder buffer, int size) {
        makeGraphRow(buffer, size, BOTTOM_LEFT_CORNER, BOTTOM_TEE, BOTTOM_RIGHT_CORNER);
    }

    private void makeTop(StringBuilder buffer, int size) {
        makeGraphRow(buffer, size, TOP_LEFT_CORNER, TOP_TEE, TOP_RIGHT_CORNER);
    }

    private void makeGraphRow(StringBuilder buffer, int size, char start, char middle, char end) {
        buffer.append(start);
        for (int i = 0; i < size; i++) {
            buffer.append(HOR_LINE);
            if (i < size - 1) {
                buffer.append(middle);
            }
        }
        buffer.append(end);
        buffer.append(NEW_LINE);
    }
}
