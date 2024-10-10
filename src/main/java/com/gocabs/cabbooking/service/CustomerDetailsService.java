package com.gocabs.cabbooking.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gocabs.cabbooking.entity.Customer;
import com.gocabs.cabbooking.entity.Driver;
import com.gocabs.cabbooking.repository.CustomerRepository;
import com.gocabs.cabbooking.repository.DriverRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // First, try to find the user in the customer table
        Customer customer = customerRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail).orElse(null);

        if (customer != null) {
            Set<GrantedAuthority> authority = customer.getRoles().stream()
                    .map((role) -> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails
                    .User(usernameOrEmail,
                            customer.getPassword(),
                            authority
                    );
        }

        // If not found in customer, try to find the user in the driver table
        Driver driver = driverRepository.findByUserName(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username or email"));

        Set<GrantedAuthority> authority = driver.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails
                .User(usernameOrEmail,
                      driver.getPassword(),
                      authority
                );
    }
}
