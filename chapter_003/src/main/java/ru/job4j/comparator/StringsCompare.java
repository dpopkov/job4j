package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Comparator for comparing strings.
 */
public class StringsCompare implements Comparator<String> {
    /**
     * Compares two strings lexicographically.
     * @param s1 first string
     * @param s2 second string
     * @return negative value if the first string precedes the second,
     *         0 if strings are equal,
     *         or positive value if the first string succeeds the second
     */
    @Override
    public int compare(String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());
        int rst = 0;
        for (int i = 0; rst == 0 && i < minLength; i++) {
            rst = Character.compare(s1.charAt(i), s2.charAt(i));
        }
        return rst != 0 ? rst : Integer.compare(s1.length(), s2.length());
    }
}
