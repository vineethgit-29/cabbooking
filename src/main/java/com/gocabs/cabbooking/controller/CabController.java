package com.gocabs.cabbooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gocabs.cabbooking.entity.Cab;
import com.gocabs.cabbooking.service.CabService;

/*
 * CabController API Endpoints 
 * Adding more lines of code
 */

@RestController
@RequestMapping("/cab")
@CrossOrigin(origins = "http://localhost:4200/")
public class CabController {

	static Logger logger = LoggerFactory.getLogger(CabController.class);
	
	@Autowired
	private CabService cabService;


	@PostMapping("/save")
	public ResponseEntity<Cab> saveCab(@RequestBody Cab cab) {
		cabService.saveCab(cab);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getall")
	public List<Cab> getAllCabs() {
		return cabService.getAllCabs();
	}

	@GetMapping("/get")
	public ResponseEntity<Cab> getCab(@RequestParam Long id) {
		Cab cab = cabService.getCab(id);
		logger.info("inside get cab method - CabControllor class");
		return new ResponseEntity<>(cab, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Cab> updateCab(@RequestBody Cab cab) {
		Cab modifyCab = cabService.updateCab(cab);
		return new ResponseEntity<>(modifyCab, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCab(@PathVariable Long id) {
		cabService.deleteCab(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
