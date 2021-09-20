package com.parkinglot.ParkingSystemAPI.beans;

public class Parking {

    private int parkingId;
    Car car;


    public Parking() {
    }

    public Parking(int parkingId, Car car) {
        this.parkingId = parkingId;
        this.car = car;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
