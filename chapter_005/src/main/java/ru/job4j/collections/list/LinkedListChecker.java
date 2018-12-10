package ru.job4j.collections.list;

/**
 * Contains methods that inspect linked lists.
 */
public class LinkedListChecker {
    /**
     * Checks if the linked list starting with the specified first node has a cycle.
     * @param first first node of the list
     * @param <T> type of list elements
     * @return true if has cycle, false otherwise
     */
    public static <T> boolean hasCycle(Node<T> first) {
        Node<T> fast = first;
        Node<T> slow = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
