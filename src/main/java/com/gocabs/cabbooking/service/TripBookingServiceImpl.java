package com.gocabs.cabbooking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocabs.cabbooking.entity.Cab;
import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.entity.TripBooking;
import com.gocabs.cabbooking.exception.ResourceNotFoundException;
import com.gocabs.cabbooking.repository.CabRepository;
import com.gocabs.cabbooking.repository.CustomerRepository;
import com.gocabs.cabbooking.repository.TripBookingRepository;

@Service
public class TripBookingServiceImpl implements TripBookingService {
	
	static Logger logger = LoggerFactory.getLogger(TripBookingServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TripBookingRepository tripBookingRepository;

	@Autowired
	private CabRepository cabRepository;

	@Override
	public TripBooking saveTrip(TripBooking tripBooking) {
		
		logger.info("inside saveTrip method - TripBookingService class");

		Long customerId = tripBooking.getCustomer().getId();

		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

		if (optionalCustomer.isEmpty()) {
			logger.error("inside saveTrip method exception occured - TripBookingService class");
			throw new ResourceNotFoundException("Customer must not be null and must have a valid ID");
		}

		Customer customer = optionalCustomer.get();

		if (tripBooking.getFromLocation() == null || tripBooking.getFromLocation().isEmpty()) {
			throw new IllegalArgumentException("Select Pickup Location");
		}

		if (tripBooking.getToLocation() == null || tripBooking.getToLocation().isEmpty()) {
			throw new IllegalArgumentException("Select Drop Location");
		}

		if (tripBooking.getFromDateTime() == null) {
			throw new IllegalArgumentException("Select Trip Date");
		}

		if (tripBooking.getToDateTime() == null) {
			throw new IllegalArgumentException("Select Trip toDate");
		}

		if (tripBooking.getFromDateTime().isAfter(tripBooking.getToDateTime())) {
			throw new IllegalArgumentException("Pickup date and time must be before drop date and time");
		}

		tripBooking.setCustomer(customer);

		tripBooking = generateBill(tripBooking);

		logger.info("existing saveTrip method - TripBookingService class");
		
		return tripBookingRepository.save(tripBooking);
	}

	private TripBooking generateBill(TripBooking tripBooking) {
		Long cabId = tripBooking.getCab().getId();
		Cab cab = cabRepository.findById(cabId)
				.orElseThrow(() -> new ResourceNotFoundException("Cab must not be null and must have a valid ID"));
		double distance = tripBooking.getDistance();
		double bill = cab.calculateBill(distance);
		tripBooking.setBill(bill);

		return tripBooking;
	}

	@Override
	public TripBooking getTripBookingDetails(Long id) {
		Optional<TripBooking> tripDetails = tripBookingRepository.findById(id);
		if (tripDetails.isEmpty()) {
			throw new ResourceNotFoundException("TripBooking Not Found With Id:" + id);
		}
		TripBooking trip = tripDetails.get();
		return trip;
	}

	@Override
	public List<TripBooking> getAllTripBookingDetails() {
		return tripBookingRepository.findAll();
	}

	@Override
	public TripBooking updateTrip(TripBooking tripBooking) {
		Optional<TripBooking> updatetrip = tripBookingRepository.findById(tripBooking.getId());
		if (updatetrip.isEmpty()) {
			throw new ResourceNotFoundException("TripBooking Not Found With Id:" + tripBooking.getId());
		}
		return tripBookingRepository.save(tripBooking);
	}

	@Override
	public void deleteTripBooking(Long id) {
		Optional<TripBooking> deleteTrip = tripBookingRepository.findById(id);
		if (deleteTrip.isEmpty()) {
			throw new ResourceNotFoundException("TripBooking Not Found With Id:" + id);
		}
		tripBookingRepository.deleteById(id);
	}

	@Override
	public List<TripBooking> getAllTripBookingDetailsByCustomerId(Long id) {
		return tripBookingRepository.findTripsByCustomerId(id);
	}

	@Override
	public List<TripBooking> getAllTripBookingDetailsByDriverId(Long id) {
		return tripBookingRepository.findTripsByDriverId(id);
	}

}
