package com.gocabs.cabbooking.service;

import java.util.List;

import com.gocabs.cabbooking.entity.Cab;

public interface CabService {
	public Cab saveCab(Cab cab);

	public List<Cab> getAllCabs();

	public Cab getCab(Long id);

	public Cab updateCab(Cab cab);

	public void deleteCab(Long id);
}
