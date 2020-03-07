package ru.job4j.ood.carparking;

/**
 * Represents minimal parking spot that can by occupied by a car.
 * Several car spots can be occupied by a larger vehicle.
 */
public class CarSpot extends Spot {

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return "CarSpot{occupied=" + occupied + '}';
    }
}
