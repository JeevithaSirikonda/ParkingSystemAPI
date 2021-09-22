package com.parkinglot.ParkingSystemAPI.service;

import com.parkinglot.ParkingSystemAPI.beans.Car;
import com.parkinglot.ParkingSystemAPI.beans.Parking;
import com.parkinglot.ParkingSystemAPI.beans.ParkingRepository;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;

import java.util.Map;

public class ParkingLotServiceImpl implements ParkingLotService{

    Parking parking;
    ParkingRepository parkingRepository;

    @Override
    public ParkingRepository buildParking(Integer parkingSize) {
        parkingRepository = new ParkingRepository(parkingSize);
        System.out.println("ParkingRepository Built");
        return parkingRepository;
    }

    @Override
    public Car parkTheCar(Car car) {
        if(car == null){
            try {
                throw new MyException("Invalid Car Entry");
            } catch (MyException e) {
                System.out.println("Exception: "+e.toString());
            }
        }
        try{
            int availableSlot = getAvailableParkingSlot(parkingRepository);
            Map<Integer,Car> parkingList = parkingRepository.getParkingList();
            if(availableSlot > 0){

                for(Map.Entry entry : parkingList.entrySet()){
                    int key = (int) entry.getKey();
                    if(entry.getValue() != null && car.getRegNumber().equalsIgnoreCase(((Car) entry.getValue()).getRegNumber())){
                        System.out.println("Car with regNum "+car.getRegNumber()+" already parked in SlotNo: "+ key);
                        return new Car();
                    }
                }
                car.setParkingId(availableSlot);
                parkingList.put(availableSlot, car);
                parkingRepository.setParkingList(parkingList);
                System.out.println("Car parked in Slot: "+availableSlot);

            }
        } catch(NullPointerException ne){
            System.out.println(ne.getMessage().toString());
            ne.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return car;
    }

    @Override
    public ParkingRepository unParkCar(Integer slotNo) {

        try{
            Map<Integer, Car> parkingList = parkingRepository.getParkingList();
            if(parkingList.containsKey(slotNo)){
                if(parkingList.get(slotNo) != null){
                    parkingList.put(slotNo, null);
                    System.out.println("Unparked Car from slot: "+slotNo);
                } else{
                    throw new MyException("Slot "+slotNo+" is already empty");
                }
            }
            parkingRepository.setParkingList(parkingList);
        } catch(NullPointerException | MyException ne){
            System.out.println("Exception: "+ne.toString());
        }
        return parkingRepository;
    }

    @Override
    public Map<Integer, Car> getParkingList() {
        return parkingRepository.getParkingList();
    }


    @Override
    public int getSlotOfParkedCar(String regNumber) {

        try{
            for(Map.Entry entry : parkingRepository.getParkingList().entrySet()){
                int key = (int) entry.getKey();
                if(entry.getValue() != null && regNumber.equalsIgnoreCase(((Car) entry.getValue()).getRegNumber())){
                    System.out.println("Car "+regNumber+" parked in SlotNo: "+ key);
                    return key;
                } else{
                    System.out.println("Car with RegNum "+regNumber+" didn't enter ParkingLot");
                }
            }
        } catch(NullPointerException ne){
            System.out.println(ne.getMessage().toString());
        }
        return 0;
    }

    private int getAvailableParkingSlot(ParkingRepository parkingRepository) {

        int key;
        for(Map.Entry entry : parkingRepository.getParkingList().entrySet()){
            key = (int) entry.getKey();
            if(entry.getValue() == null){
                return key;
            }
        }
        return 0;
    }
}
