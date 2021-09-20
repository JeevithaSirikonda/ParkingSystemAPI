package com.parkinglot.ParkingSystemAPI.beans;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

public class ParkingRepository {

    private Map<Integer, Car> parkingList;
    private int max_slots;

    public ParkingRepository() {
    }

    public ParkingRepository(int max_slots) {
        this.max_slots = max_slots;
        this.parkingList = new TreeMap<Integer, Car>();
        for(int i = 1; i<= max_slots; i++){
            this.parkingList.put(i, null);
        }
    }

    public Map<Integer, Car> getParkingList() {
        return parkingList;
    }

    public void setParkingList(Map<Integer, Car> parkingList) {
        this.parkingList = parkingList;
    }

    public int getMax_slots() {
        return max_slots;
    }

    public void setMax_slots(int max_slots) {
        this.max_slots = max_slots;
    }


}
