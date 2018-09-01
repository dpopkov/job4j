package ru.job4j.tittactoe;

import org.junit.Test;
import ru.job4j.tictactoe.Figure3T;
import ru.job4j.tictactoe.Logic3T;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Logic3TTest {
    @Test
    public void whenRowHasXWinnerThenTrue() {
        Figure3T[][] table = stringsToTable("O O",
                                                  "XOO",
                                                  "XXX");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerX(), is(true));
    }

    @Test
    public void whenColumnHasXWinnerThenTrue() {
        Figure3T[][] table = stringsToTable("OXO",
                                                  " XO",
                                                  "OXX");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasNoXWinnerThenFalse() {
        Figure3T[][] table = stringsToTable("O O",
                                                  "OXX",
                                                  "XXO");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerX(), is(false));
    }

    @Test
    public void whenRowHasOWinnerThenTrue() {
        Figure3T[][] table = stringsToTable("X X",
                                                  "XXO",
                                                  "OOO");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerO(), is(true));
    }

    @Test
    public void whenColumnHasOWinnerThenTrue() {
        Figure3T[][] table = stringsToTable("OXO",
                                                  " OO",
                                                  "OXO");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerO(), is(true));
    }

    @Test
    public void whenLeftDiagonalHasXWinnerThenTrue() {
        Figure3T[][] table = stringsToTable("XO ",
                "OX ",
                "OXX");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerX(), is(true));
    }

    @Test
    public void whenRightDiagonalHasOWinnerThenTrue() {
        Figure3T[][] table = stringsToTable("XXO",
                                                  "XO ",
                                                  "OXO");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerO(), is(true));
    }

    @Test
    public void whenHasNoOWinnerThenFalse() {
        Figure3T[][] table = stringsToTable("O O",
                                                  "OXX",
                                                  "XXO");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerO(), is(false));
    }

    @Test
    public void whenHasGapThenTrue() {
        Figure3T[][] table = stringsToTable("OXX",
                                                  "XOX",
                                                  "OX ");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.hasGap(), is(true));
    }

    @Test
    public void whenNoGapsThenFalse() {
        Figure3T[][] table = stringsToTable("OXX",
                                                  "XOX",
                                                  "XXO");
        Logic3T logic = new Logic3T(table);
        assertThat(logic.hasGap(), is(false));
    }

    private static Figure3T[][] stringsToTable(String... lines) {
        Figure3T[][] table = new Figure3T[3][3];
        for (int i = 0; i < lines.length; i++) {
            String[] symbols = lines[i].split("");
            for (int j = 0; j < symbols.length; j++) {
                Figure3T figure = null;
                switch (symbols[j]) {
                    case "X": figure = new Figure3T(true); break;
                    case "O": figure = new Figure3T(false); break;
                    case " ": figure = new Figure3T(); break;
                    default: throw new IllegalArgumentException("Invalid symbol: " + symbols[j]);
                }
                table[i][j] = figure;
            }
        }
        return table;
    }
}
