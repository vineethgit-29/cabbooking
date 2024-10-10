package com.gocabs.cabbooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gocabs.cabbooking.entity.TripBooking;
import com.gocabs.cabbooking.service.TripBookingService;

@RestController
@RequestMapping("/tripbooking")
public class TripBookingController {
	
	  static Logger logger = LoggerFactory.getLogger(TripBookingController.class);

	@Autowired
	private TripBookingService tripBookingService;

	@PostMapping("/save")
	public ResponseEntity<TripBooking> saveTrip(@RequestBody TripBooking tripBooking) {
		logger.info("inside TripBookingController - saveTrip method");
		
		tripBookingService.saveTrip(tripBooking);
		
		logger.info("existing from TripBookingController - saveTrip method");
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@GetMapping("/get")
	public ResponseEntity<TripBooking> getTripDetails(@RequestParam Long id) {
		TripBooking tripBooking = tripBookingService.getTripBookingDetails(id);
		return new ResponseEntity<>(tripBooking, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public List<TripBooking> getAllTripBookings() {
		return tripBookingService.getAllTripBookingDetails();
	}
	
	@GetMapping("/getall_ByCustomerId")
	public List<TripBooking> getAllTripBookingDetailsByCustomerId(@RequestParam Long id){
		return tripBookingService.getAllTripBookingDetailsByCustomerId(id);
	}
	
	@GetMapping("/getall_ByDriverId")
	public List<TripBooking> getAllTripBookingDetailsByDriverId(@RequestParam Long id){
		return tripBookingService.getAllTripBookingDetailsByDriverId(id);
	}

	@PutMapping("/update")
	public ResponseEntity<TripBooking> updateTripBooking(@RequestBody TripBooking tripBooking) {
		TripBooking updateTripBooking = tripBookingService.updateTrip(tripBooking);
		return new ResponseEntity<>(updateTripBooking, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
		tripBookingService.deleteTripBooking(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
