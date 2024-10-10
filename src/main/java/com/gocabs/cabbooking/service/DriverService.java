package com.gocabs.cabbooking.service;

import java.util.List;

import com.gocabs.cabbooking.entity.Driver;

public interface DriverService {

	public Driver saveDriverDetails(Driver driver);

//	public Driver saveAllDriverDetails();

	public Driver getDriverDetails(Long id);

	public List<Driver> getAllDriverDetails();
	
	public Driver updateDriver(Driver driver);

	public void detailDriverDetails(Long id);
}
