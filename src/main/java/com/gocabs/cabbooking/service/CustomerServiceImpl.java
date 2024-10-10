package com.gocabs.cabbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.exception.ResourceNotFoundException;
import com.gocabs.cabbooking.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNo(customer.getMobileNo());
		if (optionalCustomer.isPresent()) {
			throw new ResourceNotFoundException("Customer Already Exist With MobileNo:" + customer.getMobileNo());
		}
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer Not Found With Id:" + id);
		}
		Customer customer = optionalCustomer.get();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Custome Not Found With Id:" + customer.getId());
		}
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Custome Not Found With Id:" + id);
		}
		Customer customer = optionalCustomer.get();
		customer.setRoles(null);
		customerRepository.delete(customer);
	}

}
