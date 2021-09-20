package com.parkinglot.ParkingSystemAPI.service;

import com.parkinglot.ParkingSystemAPI.beans.Car;
import com.parkinglot.ParkingSystemAPI.beans.ParkingRepository;

import java.util.Map;

public interface ParkingLotService {

    int getSlotOfParkedCar(String regNumber);

    ParkingRepository buildParking(Integer parkingSize);

    Car parkTheCar(Car car);

    ParkingRepository unParkCar(Integer slotNo);

    Map<Integer, Car> getParkingList();
}
