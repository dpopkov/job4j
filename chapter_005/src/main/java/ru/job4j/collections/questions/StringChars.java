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
        Map<Character, Integer> map1 = buildCharFrequencies(s1);
        Map<Character, Integer> map2 = buildCharFrequencies(s2);
        return map1.equals(map2);
    }

    /**
     * Строит таблицу содержащую символы и частоту символов в строке.
     * @param s анализируемая строка
     * @return таблица частот символов
     */
    private Map<Character, Integer> buildCharFrequencies(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }
        return map;
    }

    /**
     * Возвращает список символов-дубликатов содержащихся в слове.
     * @param s проверяемое слово
     * @return список символов-дубликатов
     */
    public List<Character> getDuplicates(String s) {
        Set<Character> dupes = new HashSet<>();
        Set<Character> unique = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (unique.contains(ch)) {
                dupes.add(ch);
            } else {
                unique.add(ch);
            }
        }
        return new ArrayList<>(dupes);
    }
}
