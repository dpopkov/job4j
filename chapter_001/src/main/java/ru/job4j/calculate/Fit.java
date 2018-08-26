package ru.job4j.calculate;

/**
 * The program for calculating the ideal weight.
 */
public class Fit {

    private static final int MAN_HEIGHT_DEDUCTION = 100;
    private static final int WOMAN_HEIGHT_DEDUCTION = 110;
    private static final double BODY_TYPE_FACTOR = 1.15;

    /**
     * Ideal weight for a man.
     * @param height height of a man
     * @return ideal weight
     */
    public double manWeight(double height) {
        return (height - MAN_HEIGHT_DEDUCTION) * BODY_TYPE_FACTOR;
    }

    /**
     * Ideal weight for a woman
     * @param height height of a woman
     * @return ideal weight
     */
    public double womanWeight(double height) {
        return (height - WOMAN_HEIGHT_DEDUCTION) * BODY_TYPE_FACTOR;
    }
}
