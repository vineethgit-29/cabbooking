package com.gocabs.cabbooking.service;

import java.util.List;

import com.gocabs.cabbooking.entity.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer);

	public Customer getCustomer(Long id);

	public List<Customer> getAllCustomer();

	public Customer updateCustomer(Customer customer);

	public void deleteCustomer(Long id);
}
