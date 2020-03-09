package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static ru.job4j.ood.carparking.Parking.*;

public class ParkingTest {
    private final SpaceFinder spaceManager = new SpaceManager();

    @Test
    public void whenCarDrivesInThenGivesTicket() {
        Parking parking = new Parking(1, 0, spaceManager);
        Car car = makeCar("1");
        Ticket ticket = parking.driveIn(car);
        assertThat(ticket.getVehicleId(), is("1"));
    }

    @Test
    public void whenTruckDrivesInThenGivesTicket() {
        Parking parking = new Parking(0, 1, spaceManager);
        Truck truck = makeTruck("2");
        Ticket ticket = parking.driveIn(truck);
        assertThat(ticket.getVehicleId(), is("2"));
    }

    @Test
    public void whenCarDrivesInFullParkingThenGivesNoTicket() {
        Parking parking = new Parking(1, 0, spaceManager);
        Car car1 = makeCar("3");
        Car car2 = makeCar("4");
        parking.driveIn(car1);
        Ticket ticket = parking.driveIn(car2);
        assertNull(ticket);
    }

    @Test
    public void whenTruckDrivesInFullParkingThenGivesNoTicket() {
        Parking parking = new Parking(0, 1, spaceManager);
        Truck truck1 = makeTruck("5");
        Truck truck2 = makeTruck("6");
        parking.driveIn(truck1);
        Ticket ticket = parking.driveIn(truck2);
        assertNull(ticket);
    }

    @Test
    public void whenTruckDrivesInParkingWithCarSpotsOnlyThenGivesTicket() {
        Parking parking = new Parking(DEFAULT_TRUCK_SIZE, 0, spaceManager);
        Truck truck = makeTruck("7");
        Ticket ticket = parking.driveIn(truck);
        assertThat(ticket.getVehicleId(), is("7"));
    }

    @Test
    public void whenLeaveByTicketThenAcceptsTicketAndReturnsCar() {
        Parking parking = new Parking(1, 1, spaceManager);
        Car car = makeCar("8");
        Truck truck = makeTruck("9");
        Ticket carTicket = parking.driveIn(car);
        Ticket truckTicket = parking.driveIn(truck);
        Vehicle returnedCar = parking.leave(carTicket);
        Vehicle returnedTruck = parking.leave(truckTicket);
        assertThat(returnedCar, is(car));
        assertThat(returnedTruck, is(truck));
    }

    @Test
    public void whenLeaveByTicketThenCanAcceptMoreCars() {
        Parking parking = new Parking(DEFAULT_TRUCK_SIZE, 0, spaceManager);
        Truck truck = makeTruck("10");
        Ticket truckTicket = parking.driveIn(truck);
        assertThat(truckTicket.getVehicleId(), is("10"));
        parking.leave(truckTicket);
        Car car = makeCar("11");
        Ticket carTicket = parking.driveIn(car);
        assertNotNull(carTicket);
        assertThat(carTicket.getVehicleId(), is("11"));
    }

    @Test
    public void whenLeaveByInvalidTicketThenReturnsNothing() {
        Parking parking = new Parking(1, 0, spaceManager);
        parking.driveIn(makeCar("12"));
        Vehicle returnedCar = parking.leave(new Ticket("wrongID", -1));
        assertNull(returnedCar);
    }

    private Car makeCar(String number) {
        return new Car(new LicensePlateNumber(number));
    }

    private Truck makeTruck(String number) {
        return new Truck(new LicensePlateNumber(number), Parking.DEFAULT_TRUCK_SIZE);
    }
}
