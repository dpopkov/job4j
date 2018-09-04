package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Denis Popkov
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenUnique() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"Привет", "Мир", "Супер"};
        assertThat(result, is(expected));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void whenRemoveMoreDuplicatesThenUniqueAgain() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = "aaabbbaaaaaccccaaaddddccbcacbadcccccaaaaaaaabccccbcdddddddac".split("");
        String[] result = arrayDuplicate.remove(input);
        String[] expected = "acdb".split("");
        assertThat(result, is(expected));
    }

    @Test
    public void whenOneElementThenDoNotRemove() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"a"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"a"};
        assertThat(result, is(expected));
    }
}
