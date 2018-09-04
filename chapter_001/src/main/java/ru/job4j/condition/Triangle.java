package ru.job4j.condition;

/**
 * Represents triangle.
 *
 * @author Denis Popkov
 */
public class Triangle {
    private final Point a;
    private final Point b;
    private final Point c;

    /**
     * Constructs and initializes triangle with 3 vertices.
     * @param a vertex 1
     * @param b vertex 2
     * @param c vertex 3
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Calculates and returns half perimeter using specified side lengths.
     * @param ab length of 1st side
     * @param ac length of 2nd side
     * @param bc length of 3rd side
     * @return half perimeter
     */
    public double halfPerimeter(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2.0;
    }

    /**
     * Calculates area of the triangle.
     * @return area or -1 if it is not possible to construct a triangle
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.halfPerimeter(ab, ac, bc);
        if (this.exists(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Checks whether it is possible to construct a triangle with specified lengths of sides.
     * @param ab side 1
     * @param ac side 2
     * @param bc side 3
     * @return true if it is possible to construct a triangle, false otherwise
     */
    private boolean exists(double ab, double ac, double bc) {
        return ab < (ac + bc) && ac < (ab + bc) && bc < (ab + ac);
    }
}
