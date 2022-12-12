package com.example.pirateservice;

import com.example.pirateservice.models.Pirate;
import com.example.pirateservice.models.Ship;
import com.example.pirateservice.repositories.PirateRepository;
import com.example.pirateservice.repositories.ShipRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
class PirateserviceApplicationTests {


	//dependency injection
	@Autowired
	PirateRepository pirateRepository;

	@Autowired
	ShipRepository shipRepository;   // We need to inject the ship repository as well.

	@Test
	void contextLoads() {
	}

	@Test
	public void createPirateAndShip() {

		Ship ship = new Ship("The Black Pearl");
		shipRepository.save(ship);

//		Pirate john = new Pirate("John", "Silver", 30, ship);
//		pirateRepository.save(john);

//		Pirate jack = new Pirate("Jack", "Sparrow", 30, ship);
//		pirateRepository.save(jack);
	}




}
