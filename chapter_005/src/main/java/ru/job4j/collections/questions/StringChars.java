package ru.job4j.collections.questions;

import java.util.*;

/**
 * Содержит методы по анализу строк.
 */
public class StringChars {
    /**
     * Проверяет содержат ли сравниваемые слова один и тот же набор символов.
     * Символы могут дублироваться в пределах слова.
     * @param s1 первое слово
     * @param s2 второе слово
     * @return true если оба слова содержат одиноковый набор символов, иначе false
     */
    public boolean haveSameCharacters(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        var map = new HashMap<Character, Integer>();
        s1.chars().forEach(ch -> map.merge((char) ch, 1, Integer::sum));
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if (!map.containsKey(ch)) {
                return false;
            }
            map.computeIfPresent(ch, (k, v) -> (v == 1) ? null : v - 1);
        }
        return map.isEmpty();
    }

    /**
     * Возвращает список символов-дубликатов содержащихся в слове.
     * @param s проверяемое слово
     * @return список символов-дубликатов
     */
    public List<Character> getDuplicates(String s) {
        Set<Character> dupes = new HashSet<>();
        Set<Character> allChars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (allChars.contains(ch)) {
                dupes.add(ch);
            } else {
                allChars.add(ch);
            }
        }
        return new ArrayList<>(dupes);
    }
}
