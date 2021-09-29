package com.parkinglot.ParkingSystemAPI.beans;

public class Car {

    private String regNumber;
    private String color;

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


    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
