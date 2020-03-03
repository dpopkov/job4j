package ru.job4j.ood.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * Contains methods for finding extreme elements using a comparator.
 */
public class MaxMin {

    /**
     * Finds maximum element in the specified list of values.
     * @param values non-empty list of values
     * @param comparator comparator used for search
     * @param <T> type of elements
     * @return maximum element
     */
    public <T> T max(List<T> values, Comparator<T> comparator) {
        return findByPredicate(values, comparator, x -> x < 0);
    }

    /**
     * Finds minimum element in the specified list of values.
     * @param values non-empty list of values
     * @param comparator comparator used for search
     * @param <T> type of elements
     * @return minimum element
     */
    public <T> T min(List<T> values, Comparator<T> comparator) {
        return findByPredicate(values, comparator, x -> x > 0);
    }

    /** Uses predicate for specifying minimum or maximum selection condition. */
    private <T> T findByPredicate(List<T> values, Comparator<T> comparator, IntPredicate selectionCondition) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Method needs non-empty list of values");
        }
        T result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            T next = values.get(i);
            if (selectionCondition.test(comparator.compare(result, next))) {
                result = next;
            }
        }
        return result;
    }
}
