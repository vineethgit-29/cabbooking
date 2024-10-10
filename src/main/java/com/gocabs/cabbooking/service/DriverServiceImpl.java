package com.gocabs.cabbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocabs.cabbooking.entity.Driver;
import com.gocabs.cabbooking.exception.ResourceNotFoundException;
import com.gocabs.cabbooking.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Driver saveDriverDetails(Driver driver) {
		Optional<Driver> optionalDriver = driverRepository.findByLicenceNo(driver.getLicenceNo());
		if (optionalDriver.isPresent()) {
			throw new ResourceNotFoundException("Driver Already Exists With LicenceNo:" + driver.getLicenceNo());
		}
		return driverRepository.save(driver);
	}

	@Override
	public Driver getDriverDetails(Long id) {
		Optional<Driver> optionalDriver = driverRepository.findById(id);
		if (optionalDriver.isEmpty()) {
			throw new ResourceNotFoundException("Driver Not Found With LicenceNo:" + id);
		}
		Driver driver = optionalDriver.get();
		return driver;
	}

	@Override
	public List<Driver> getAllDriverDetails() {
		return driverRepository.findAll();
	}

	@Override
	public Driver updateDriver(Driver driver) {
		Optional<Driver> optionalDriver = driverRepository.findById(driver.getId());
		if (optionalDriver.isEmpty()) {
			throw new ResourceNotFoundException("Driver Not Found With id:" + driver.getId());
		}
		return driverRepository.save(driver);
	}

	@Override
	public void detailDriverDetails(Long id) {
		Optional<Driver> optionalDriver = driverRepository.findById(id);
		if (optionalDriver.isEmpty()) {
			throw new ResourceNotFoundException("Driver Not Found With id:" + id);
		}
		Driver driver = optionalDriver.get();
		driver.setRoles(null);
		driverRepository.delete(driver);
	}

}
