package com.gocabs.cabbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	private Customer customer1;
	private Customer customer2;

	@BeforeEach
	public void setUp() {
		customer1 = new Customer();
		customer1.setUserName("babu");
		customer1.setPassword("babu123");
		customer1.setId(1L);
		customer1.setCustomerName("babu");
		customer1.setEmail("babu@gmail.com");
		customer1.setMobileNo("589577845");
		customer1.setCustomerAddress(null);
		customer1.setTripBooking(null);

		customer2 = new Customer();
		customer2.setUserName("bean");
		customer2.setPassword("bean123");
		customer2.setId(2L);
		customer2.setCustomerName("bean");
		customer2.setEmail("bean@gmail.com");
		customer2.setMobileNo("789878988");
		customer2.setCustomerAddress(null);
		customer2.setTripBooking(null);

	}

	@Test
	void testGetCustomer() {
		Optional<Customer> optionalcustomer = Optional.of(customer1);
		when(customerRepository.findById(customer1.getId())).thenReturn(optionalcustomer);
		Customer customer = customerService.getCustomer(customer1.getId());
		assertEquals(customer1.getCustomerName(), customer.getCustomerName());
	}

	@Test
	void testGetAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);

		when(customerRepository.findAll()).thenReturn(customers);
		List<Customer> allCustomers = customerService.getAllCustomer();
		assertEquals(2, allCustomers.size());
	}

	@Test
	void testSaveCustomer() {
//		Optional<Customer> optionalcustomer = Optional.of(customer);
		when(customerRepository.findByMobileNo(customer1.getMobileNo())).thenReturn(Optional.empty());
		when(customerRepository.save(customer1)).thenReturn(customer1);
		Customer savedCustomer = customerService.saveCustomer(customer1);
		assertEquals(customer1.getId(), savedCustomer.getId());
	}

	@Test
	void testUpdateCustomer() {
		Optional<Customer> optionalCustomer = Optional.of(customer1);
		when(customerRepository.findById(customer1.getId())).thenReturn(optionalCustomer);
		Customer updtcustomer = new Customer();
		updtcustomer.setUserName("babu");
		updtcustomer.setPassword("babu123");
		updtcustomer.setId(1L);
		updtcustomer.setCustomerName("babu");
		updtcustomer.setEmail("babu@gmail.com");
		updtcustomer.setMobileNo("7895874578");
		when(customerRepository.save(updtcustomer)).thenReturn(updtcustomer);
		Customer updatedCustomer = customerService.updateCustomer(updtcustomer);
		assertEquals(customer1.getEmail(), updatedCustomer.getEmail());
		assertEquals(customer1.getUserName(), updatedCustomer.getUserName());
	}

	@Test
	void testDeleteCustomer() {
		Optional<Customer> optionalCustomer = Optional.of(customer1);
		when(customerRepository.findById(customer1.getId())).thenReturn(optionalCustomer);
		customerService.deleteCustomer(customer1.getId());
		verify(customerRepository, times(1)).findById(customer1.getId());
	}
}
