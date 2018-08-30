package ru.job4j.array;

/**
 * String wrapper.
 *
 * @author Denis Popkov
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        data = line.toCharArray();
    }

    /**
     * Checks that word begins with specified prefix.
     * @param prefix starting prefix
     * @return true if word begins with prefix, false otherwise
     */
    public boolean startsWith(String prefix) {
        boolean result = true;
        char[] prefixChars = prefix.toCharArray();
        for (int i = 0; i < prefixChars.length; i++) {
            if (data[i] != prefixChars[i]) {
                result = false;
                break;
            }
        }

        return result;
    }
}
