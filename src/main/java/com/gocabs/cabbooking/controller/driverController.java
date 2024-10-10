package com.gocabs.cabbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gocabs.cabbooking.entity.Driver;
import com.gocabs.cabbooking.service.DriverService;

@RestController
@RequestMapping("/driver")
public class driverController {
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping("/save")
	public ResponseEntity<Driver> saveDriverDerails(@RequestBody Driver driver) {
		driverService.saveDriverDetails(driver);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<Driver> getDriverDetails(@RequestParam Long id) {
		Driver driver = driverService.getDriverDetails(id);
		return new ResponseEntity<>(driver, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public List<Driver> getAllDriverDetails() {
		return driverService.getAllDriverDetails();
	}

	@PutMapping("/update")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) {
		Driver updatedDriver = driverService.updateDriver(driver);
		return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteDriverDetails(@PathVariable Long id) {
		driverService.detailDriverDetails(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
