package ru.job4j.array;

/**
 * Contains method for merging sorted arrays.
 */
public class MergeArrays {
    /**
     * Merges two sorted arrays into one.
     * @param a first sorted array
     * @param b second sorted array
     * @return sorted array containing all elements of source arrays
     */
    public int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int ai = 0, bi = 0;
        for (int i = 0; i < result.length; i++) {
            if (ai < a.length && bi < b.length) {
                result[i] = a[ai] < b[bi] ? a[ai++] : b[bi++];
            } else if (ai < a.length) {
                result[i] = a[ai++];
            } else if (bi < b.length) {
                result[i] = b[bi++];
            }
        }
        return result;
    }
}
