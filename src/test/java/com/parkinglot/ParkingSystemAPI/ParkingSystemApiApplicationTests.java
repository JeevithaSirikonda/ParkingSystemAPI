package com.parkinglot.ParkingSystemAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkinglot.ParkingSystemAPI.beans.Car;
import com.parkinglot.ParkingSystemAPI.beans.ParkingRepository;
import com.parkinglot.ParkingSystemAPI.controller.ParkingLotController;
import com.parkinglot.ParkingSystemAPI.service.ParkingLotService;
import com.parkinglot.ParkingSystemAPI.service.ParkingLotServiceImpl;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
@WebMvcTest(controllers = ParkingLotController.class)
class ParkingSystemApiApplicationTests {

	Result result = JUnitCore.runClasses(ParkingSystemApiApplicationTests.class);


	@Autowired
	private MockMvc mockMvc;

	ParkingLotService mockParkingLotService = new ParkingLotServiceImpl();

	@InjectMocks
	ParkingLotController parkingLotController = new ParkingLotController();

	@MockBean
	ParkingRepository parkingRepository;

	ObjectMapper mapper = new ObjectMapper();

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
	public void testParking() throws Exception {
		System.out.println("inside testParking()");

		parkingRepository = mockParkingLotService.buildParking(10);
		System.out.println(parkingRepository.getMax_slots());

		mockMvc.perform(MockMvcRequestBuilders.get("/parking/buildParking")
						.contentType(MediaType.APPLICATION_JSON)
						.queryParam("id", "10"))
				.andReturn();

		MvcResult result = (MvcResult) mockMvc.perform(post("/parking/park")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Car("REG1","BLUE"))))
				.andReturn();

		String actualResponse = result.getResponse().getContentAsString();
		System.out.println(actualResponse);
		String ExpectedResponse = "{\"regNumber\":\"REG1\",\"color\":\"BLUE\",\"parkingId\":1}";
		Assert.assertEquals(ExpectedResponse, actualResponse);

		//test unpark()
		mockMvc.perform(post("/parking/unpark")
						.contentType(MediaType.APPLICATION_JSON)
				.queryParam("id", String.valueOf(3)))
				.andExpect(status().isOk()).andReturn();
		System.out.println("unparking done");

		//test getSlotDetails()
		MvcResult res = (MvcResult)
				mockMvc.perform(get("/parking/getSlot")
				.contentType(MediaType.APPLICATION_JSON)
				.queryParam("regNumber", String.valueOf("REG1"))).andReturn();

		Assert.assertEquals("1", res.getResponse().getContentAsString());

	}

	@Test
	@Order(3)
	void whenInvalidCarEnterThen400() throws Exception {
		System.out.println("inside testParking() InvalidCase");
		JSONObject jsonRequest = new JSONObject();

		MvcResult result = (MvcResult) mockMvc.perform(post("/parking/park")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(jsonRequest.toString())))
				.andExpect(status().isBadRequest()).andReturn();


		//Assert.assertEquals(car1, mapper.readValue(result.getResponse().getContentAsString(), Car.class));
	}


}
