package ru.job4j.condition;

/**
 * Describes a point in a two-dimensional (X, Y) coordinate system.
 *
 * @author Denis Popkov
 */
public class Point {
    private int x;
    private int y;

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

    /**
     * Entry point to application.
     * @param args not used
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);

        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double distance = a.distanceTo(b);
        System.out.printf("Distance between points a and b: %f%n", distance);
    }
}
