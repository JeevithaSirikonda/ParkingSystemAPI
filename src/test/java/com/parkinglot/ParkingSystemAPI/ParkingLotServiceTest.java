package com.parkinglot.ParkingSystemAPI;

import com.parkinglot.ParkingSystemAPI.beans.Car;
import com.parkinglot.ParkingSystemAPI.beans.ParkingRepository;
import com.parkinglot.ParkingSystemAPI.service.ParkingLotService;
import com.parkinglot.ParkingSystemAPI.service.ParkingLotServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ParkingLotServiceTest {
    @Mock
    ParkingLotService mockParkingLotService = mock(ParkingLotService.class);
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();

    ParkingRepository parkingRepository = new ParkingRepository();

    static Car car1, car2,car3,car4,car5,car6,car7;
    @BeforeClass
    public static void setUp(){
        System.out.println("inside setUp()");
        car1 = new Car("REG1", "BLUE");
        car2 = new Car("REG2", "RED");
        car3 = new Car("REG3", "GREEN");
        car4 = new Car("REG4", "BLUE");
        car5 = new Car("REG5", "RED");
        car6 = new Car("REG6", "BLUE");
        car7 = new Car("REG2","YELLOW");
    }

    @Test
    public void testBuildParking(){

        System.out.println(car1.toString());
        Mockito.when(mockParkingLotService.buildParking(10)).thenReturn(parkingRepository);

        parkingRepository = parkingLotService.buildParking(10);

        Assert.assertEquals(10, parkingRepository.getMax_slots());
    }
    @Test
    public void testParking(){
        parkingRepository = parkingLotService.buildParking(10);
        Mockito.when(mockParkingLotService.parkTheCar(car1)).thenReturn(car1);
        Mockito.when(mockParkingLotService.parkTheCar(car2)).thenReturn(car2);
        //test parking()
        Assert.assertEquals(parkingLotService.parkTheCar(car1).toString(), new Car("REG1","BLUE").toString());
        Assert.assertEquals(parkingLotService.parkTheCar(car2).toString(), new Car("REG2","RED").toString());
        Assert.assertEquals(parkingLotService.parkTheCar(car3).toString(), new Car("REG3","GREEN").toString());
        Assert.assertEquals(parkingLotService.parkTheCar(car4).toString(), new Car("REG4","BLUE").toString());
        Assert.assertNotEquals(parkingLotService.parkTheCar(car1).toString(), new Car("REG1","BLUE").toString());
        //test unpark()
        Mockito.when(mockParkingLotService.unParkCar(3)).thenReturn(parkingRepository);
        Mockito.when(mockParkingLotService.unParkCar(3)).thenReturn(parkingRepository);

        Assert.assertEquals(parkingLotService.unParkCar(3), parkingRepository);
        Assert.assertEquals(parkingLotService.unParkCar(3), parkingRepository);

        // test getSlotByRegNum
        Assert.assertEquals(1, parkingLotService.getSlotOfParkedCar("REG1"));
        Assert.assertEquals(0, parkingLotService.getSlotOfParkedCar("REG3"));

        System.out.println(parkingLotService.getParkingList());

    }
}

