package com.gocabs.cabbooking.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gocabs.cabbooking.config.JwtTokenProvider;
import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.entity.Driver;
import com.gocabs.cabbooking.entity.Role;
import com.gocabs.cabbooking.model.Login;
import com.gocabs.cabbooking.repository.CustomerRepository;
import com.gocabs.cabbooking.repository.DriverRepository;
import com.gocabs.cabbooking.repository.RoleRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public String login(Login login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsernameOrEmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication, null);
		return token;
	}

	@Override
	public Customer customerRegister(Customer customer) {
		Set<Role> roles = customer.getRoles();
		Set<Role> rolesAssigned = new LinkedHashSet<>();
		roles.forEach(c -> {
			Optional<Role> optionalCustomer = roleRepository.findById(c.getId());
			if (optionalCustomer.isEmpty()) {
				throw new RuntimeException("customer role not found. Registration failed.");
			}
			Role customerRole = optionalCustomer.get();
			rolesAssigned.add(customerRole);
		});
		customer.setRoles(rolesAssigned);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Driver driverRegister(Driver driver) {
		Set<Role> roles = driver.getRoles();
		Set<Role> rolesAssigned = new LinkedHashSet<>();
		roles.forEach(r -> {
			Optional<Role> optionalRole = roleRepository.findById(r.getId());
			if (optionalRole.isEmpty()) {
				throw new RuntimeException("Driver role not found. Registration failed.");
			}
			Role driverRole = optionalRole.get();
			rolesAssigned.add(driverRole);
		});
		driver.setRoles(rolesAssigned);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		driverRepository.save(driver);
		return driver;
	}

}
