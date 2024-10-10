package com.gocabs.cabbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gocabs.cabbooking.entity.Cab;

public interface CabRepository extends JpaRepository<Cab, Long> {

	public Optional<Cab> findByPlateNumber(String platenumber);
}
