package com.gocabs.cabbooking.service;

import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.entity.Driver;
import com.gocabs.cabbooking.model.Login;

public interface AuthService {

	String login(Login login);

	Customer customerRegister(Customer customer);

	Driver driverRegister(Driver driver);
}
