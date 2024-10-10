package com.gocabs.cabbooking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cabType;
	private float rating;
	private String plateNumber;
	private double ratePerKilometer;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public double calculateBill(double distance) {
		return distance * ratePerKilometer;
	}
}
