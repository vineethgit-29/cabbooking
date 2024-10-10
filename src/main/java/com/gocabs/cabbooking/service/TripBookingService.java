package com.gocabs.cabbooking.service;

import java.util.List;

import com.gocabs.cabbooking.entity.TripBooking;

public interface TripBookingService {

	public TripBooking saveTrip(TripBooking tripBooking);

	public TripBooking getTripBookingDetails(Long id);

	public List<TripBooking> getAllTripBookingDetails();
	
	public List<TripBooking> getAllTripBookingDetailsByCustomerId(Long id);
	
	public List<TripBooking> getAllTripBookingDetailsByDriverId(Long id);

	public TripBooking updateTrip(TripBooking tripBooking);

	public void deleteTripBooking(Long id);
}
