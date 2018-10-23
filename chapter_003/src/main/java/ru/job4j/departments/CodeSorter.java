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
    private List<String> sort(List<String> codes, Comparator<String> comparator) {
        SortedSet<String> sorted;
        if (comparator == null) {
            sorted = new TreeSet<>(codes);
        } else {
            sorted = new TreeSet<>(comparator);
            sorted.addAll(codes);
        }
        for (String code : codes) {
            ensureSuperCodes(sorted, code);
        }
        return new ArrayList<>(sorted);
    }

    /**
     * Implements comparator for descending order preserving hierarchy of sub-departments.
     */
    private class DescendingComparator implements Comparator<String> {
        @Override
        public int compare(String code1, String code2) {
            String[] subCodes1 = code1.split(REGEX_DELIMITER);
            String[] subCodes2 = code2.split(REGEX_DELIMITER);
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
