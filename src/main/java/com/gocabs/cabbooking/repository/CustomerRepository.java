package com.gocabs.cabbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gocabs.cabbooking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	public Optional<Customer> findByMobileNo(String mobileNo);
	
	public Optional<Customer> findByUserNameOrEmail(String username, String email);
}
