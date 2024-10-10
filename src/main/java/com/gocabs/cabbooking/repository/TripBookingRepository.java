package com.gocabs.cabbooking.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gocabs.cabbooking.entity.TripBooking;

public interface TripBookingRepository extends JpaRepository<TripBooking, Long> {

	public List<TripBooking> findByFromLocation(String fromLocation);

	public List<TripBooking> findByToLocation(String toLocation);

	public List<TripBooking> findByfromDateTime(Date fromDdate);

	public List<TripBooking> findBytoDateTime(Date toDate);
	
	@Query("Select t from TripBooking t where t.customer.id=:cid")
	public List<TripBooking> findTripsByCustomerId(@Param("cid") Long Id);
	
	@Query("select t from TripBooking t where t.cab.driver.id=:d_id")
	public List<TripBooking> findTripsByDriverId(@Param("d_id") Long Id);
	
}
