package com.gocabs.cabbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers("/cab/save/").hasAnyRole("ADMIN","DRIVER");
			authorize.requestMatchers("/cab/update/").hasRole("ADMIN");
			authorize.requestMatchers("/cab/delete/").hasRole("ADMIN");
			authorize.requestMatchers("/cab/getall/").hasAnyRole("ADMIN", "USER");
			authorize.requestMatchers("/cab/get/").hasAnyRole("ADMIN", "USER");

			authorize.requestMatchers("/tripbooking/save/").hasRole("USER");
			authorize.requestMatchers("/tripbooking/update/").hasRole("ADMIN");
			authorize.requestMatchers("/tripbooking/delete/").hasRole("USER");
			authorize.requestMatchers("/tripbooking/getall/").hasAnyRole("ADMIN", "USER");
//			authorize.requestMatchers("/tripbooking/get/").hasAnyRole("ADMIN", "USER");
			authorize.requestMatchers("/tripbooking/get/**").permitAll();

			authorize.requestMatchers("/driver/save/").hasAnyRole("ADMIN", "DRIVER");
			authorize.requestMatchers("/driver/update/").hasRole("ADMIN");
			authorize.requestMatchers("/driver/delete/").hasRole("ADMIN");
			authorize.requestMatchers("/driver/getall/").hasAnyRole("ADMIN", "DRIVER");
			authorize.requestMatchers("/driver/get/").hasAnyRole("ADMIN", "DRIVER");
			
			authorize.requestMatchers("/customer/save/").hasRole("ADMIN");
			authorize.requestMatchers("/customer/update/").hasRole("USER");
			authorize.requestMatchers("/customer/delete/").hasAnyRole("USER","ADMIN");
			authorize.requestMatchers("/customer/getall/").hasAnyRole("ADMIN", "USER");
			authorize.requestMatchers("/customer/get/").hasAnyRole("ADMIN", "USER");
			
			authorize.requestMatchers("/api/auth/**").permitAll();
			authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
			authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());

		http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
