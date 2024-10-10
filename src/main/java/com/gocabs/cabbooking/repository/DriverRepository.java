package com.gocabs.cabbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gocabs.cabbooking.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver,Long>{

	public Optional<Driver> findByLicenceNo(String licenceNo); 
	
    public Optional<Driver> findByUserName(String userName);

}
