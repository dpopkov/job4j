package ru.job4j.departments;

import java.util.*;

/**
 * Contains methods sorting departments codes.
 */
public class CodeSorter {
    /**
     * Delimiter separating sub-departments in department codes.
     */
    private static final String DELIMITER = "\\";
    /**
     * Delimiter used in regular expression.
     */
    private static final String REGEX_DELIMITER = "\\\\";

    /**
     * Sorts department codes in ascending order. Original list remains unchanged.
     * Adds sub-divisions missing in the hierarchy.
     * @param codes list of department codes
     * @return list of sorted codes
     */
    public List<String> sortAscending(List<String> codes) {
        return sort(codes, null);
    }

    /**
     * Sorts department codes in descending order. Original list remains unchanged.
     * Adds sub-divisions missing in the hierarchy.
     * @param codes list of department codes
     * @return list of sorted codes
     */
    public List<String> sortDescending(List<String> codes) {
        return sort(codes, new DescendingComparator());
    }

    /**
     * Sorts list of codes according to specified comparator.
     * If no comparator is supplied (argument is null) then sorts in natural order.
     * @param codes list of department codes
     * @param comparator department codes comparator
     * @return list of sorted codes (original list remains unchanged)
     */
    private List<String> sort(List<String> codes, Comparator<String[]> comparator) {
        List<String> sortedList;
        if (comparator == null) {
            SortedSet<String> sortedSet = new TreeSet<>(codes);
            for (String code : codes) {
                ensureSuperCodes(sortedSet, code);
            }
            sortedList = new ArrayList<>(sortedSet);
        } else {
            SortedMap<String[], String> sortedMap = listToMapOfSplittedCodes(codes, comparator);
            for (String code : codes) {
                ensureSuperCodes(sortedMap, code);
            }
            sortedList = new ArrayList<>(sortedMap.values());
        }
        return sortedList;
    }

    /**
     * Converts list of codes to sorted map where arrays of splitted sub-codes are associated to the original codes.
     * @param list list of original codes
     * @param comparator comparator that will compare splitted sub-codes
     * @return map of splitted sub-codes to codes
     */
    private SortedMap<String[], String> listToMapOfSplittedCodes(List<String> list, Comparator<String[]> comparator) {
        SortedMap<String[], String> sorted = new TreeMap<>(comparator);
        for (String code : list) {
            String[] subCodes = code.split(REGEX_DELIMITER);
            sorted.put(subCodes, code);
        }
        return sorted;
    }

    /**
     * Implements comparator for descending order preserving hierarchy of sub-departments.
     */
    private class DescendingComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] subCodes1, String[] subCodes2) {
            int minLength = Math.min(subCodes1.length, subCodes2.length);
            for (int i = 0; i < minLength; i++) {
                int rst = subCodes2[i].compareTo(subCodes1[i]);
                if (rst != 0) {
                    return rst;
                }
            }
            return Integer.compare(subCodes1.length, subCodes2.length);
        }
    }

    /**
     * Ensures that the specified sorted set contains super codes for the specified code.
     * @param set sorted set of codes
     * @param code checked code
     */
    private void ensureSuperCodes(SortedSet<String> set, String code) {
        String superCode = getSuperCode(code);
        if (superCode != null) {
            if (!set.contains(superCode)) {
                set.add(superCode);
                ensureSuperCodes(set, superCode);
            }
        }
    }

    /**
     * Ensures that the specified sorted map contains super codes for the specified code.
     * @param map sorted map of splitted sub-codes arrays to original codes
     * @param code checked code
     */
    private void ensureSuperCodes(SortedMap<String[], String> map, String code) {
        String superCode = getSuperCode(code);
        if (superCode != null && !map.containsValue(superCode)) {
            map.put(superCode.split(REGEX_DELIMITER), superCode);
            ensureSuperCodes(map, superCode);
        }
    }

    /**
     * Gets super code according to department hierarchy represented in the specified code.
     * @param code department code
     * @return super code
     */
    private static String getSuperCode(String code) {
        int pos = code.lastIndexOf(DELIMITER);
        if (pos == -1) {
            return null;
        }
        return code.substring(0, pos);
    }
}
