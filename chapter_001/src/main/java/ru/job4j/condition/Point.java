package ru.job4j.condition;

/**
 * Describes a point in a two-dimensional (X, Y) coordinate system.
 */
public class Point {
    private final int x;
    private final int y;

    /**
     * Constructs and initializes point with specified coordinates.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates distance to other point.
     * @param that other point
     * @return distance
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }
}
