package ru.job4j.collections.questions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@SuppressWarnings("SpellCheckingInspection")
public class StringCharsTest {

    @Test
    public void whenHaveSameCharactersThenTrue() {
        boolean rst = new StringChars().haveSameCharacters("ABCA", "CABA");
        assertThat(rst, is(true));
    }

    @Test
    public void whenHaveDifferentCharactersThenFalse() {
        boolean rst = new StringChars().haveSameCharacters("ABBA", "CABA");
        assertThat(rst, is(false));
    }

    @Test
    public void whenHaveSameCharactersAndDifferentAmountThenFalse() {
        boolean rst = new StringChars().haveSameCharacters("ABBC", "AABC");
        assertThat(rst, is(false));
    }

    @Test
    public void whenGetDuplicatesOnWordHavingDuplicatesThenReturnListOfDuplicates() {
        List<Character> dupes = new StringChars().getDuplicates("ABACAB");
        assertThat(dupes, is(Arrays.asList('A', 'B')));
    }

    @Test
    public void whenGetDuplicatesOnWordHavingUniqueCharsThenReturnEmptyList() {
        List<Character> dupes = new StringChars().getDuplicates("ABCD");
        assertThat(dupes.size(), is(0));
    }
}
