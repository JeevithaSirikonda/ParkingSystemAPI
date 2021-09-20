package com.parkinglot.ParkingSystemAPI.beans;

public class Car {

    private String regNumber;
    private String color;
    private int ParkingId;
    //private Map<String, Integer> ParkedCars = new HashMap<String, Integer>();

    public Car() {
    }

    public Car(String regNumber, String color) {
        this.regNumber = regNumber;
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getParkingId() {
        return ParkingId;
    }

    public void setParkingId(int parkingId) {
        this.ParkingId = parkingId;
    }


    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
