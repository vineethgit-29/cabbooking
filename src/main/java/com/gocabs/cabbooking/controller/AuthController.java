package com.gocabs.cabbooking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.entity.Driver;
import com.gocabs.cabbooking.model.Login;
import com.gocabs.cabbooking.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	 
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> signIn(@RequestBody Login login){
	    System.out.println("Received login request: " + login);
		String token = authService.login(login);
		Map<String,String> response = new HashMap<>();
		response.put("token", token);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(response,headers,HttpStatus.OK);
	}
	
	@PostMapping("/customer/register")
	public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {
		
		 authService.customerRegister(customer);

		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@PostMapping("/driver/register")
	public ResponseEntity<Driver> signUp(@RequestBody Driver driver) {
		
		 authService.driverRegister(driver);

		return new ResponseEntity<>(driver, HttpStatus.CREATED);
	}

}
