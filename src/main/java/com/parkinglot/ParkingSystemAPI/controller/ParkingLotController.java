package com.parkinglot.ParkingSystemAPI.controller;

import com.parkinglot.ParkingSystemAPI.beans.Car;
import com.parkinglot.ParkingSystemAPI.beans.Parking;
import com.parkinglot.ParkingSystemAPI.beans.ParkingRepository;
import com.parkinglot.ParkingSystemAPI.service.ParkingLotService;
import com.parkinglot.ParkingSystemAPI.service.ParkingLotServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping(path = "/parking")
public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(){
        this.parkingLotService = new ParkingLotServiceImpl();
    }
    public ParkingLotController(ParkingLotService  parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/start")
    public String home(){
        return "Welcome!";
    }

    @GetMapping("/buildParking")
    ///@RequestMapping(produces = "application/json")
    public int buildParking(@RequestParam(name = "id") Integer parkingSize){
        System.out.println("inside buildParking");
        ParkingRepository parkingRepository = parkingLotService.buildParking(parkingSize);
        return parkingRepository.getMax_slots();
    }

    @PostMapping("/park")
    @ResponseBody
    public Parking ParkNewCar(@RequestBody Car car){
        return parkingLotService.parkTheCar(car);
    }

    @PostMapping("/unpark")
    public String UnParkCar(@RequestParam(name = "id") Integer slotNo){
        parkingLotService.unParkCar(slotNo);
        return "SlotNumber "+slotNo+" is now available for parking";
    }

    @GetMapping("/getall")
    public Map<Integer, Car> GetParkingList(){
        return parkingLotService.getParkingList();
    }

    @GetMapping("/getSlot")
    public int GetSlotOfParkedCar(@RequestParam(name = "regNumber") String regNumber){
        return parkingLotService.getSlotOfParkedCar(regNumber);
    }

}
