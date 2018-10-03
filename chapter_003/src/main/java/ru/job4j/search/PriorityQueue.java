package ru.job4j.search;

import java.util.LinkedList;

/**
 * Priority queue allowing to retrieve tasks according to their priority.
 */
public class PriorityQueue {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Adds new task to the queue. The task will be inserted to position based on priority of the task.
     * @param task new task
     */
    public void put(Task task) {
        boolean searching = true;
        for (int i = 0; searching && i < tasks.size(); i++) {
            if (task.getPriority() < tasks.get(i).getPriority()) {
                tasks.add(i, task);
                searching = false;
            }
        }
        if (searching) {
            tasks.add(task);
        }
    }

    /**
     * Retrieves and removes the heading task in the queue.
     * @return heading task or null if the queue is empty
     */
    public Task take() {
        return tasks.poll();
    }
}
