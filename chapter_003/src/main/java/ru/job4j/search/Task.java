package ru.job4j.search;

/**
 * Represents prioritized task with description.
 */
public class Task {
    private final String desc;
    private final int priority;

    /**
     * Constructs task using specified descriptive information and priority.
     * @param desc descriptive information
     * @param priority priority
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    /**
     * @return descriptive information
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return task priority
     */
    public int getPriority() {
        return priority;
    }
}
