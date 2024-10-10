package com.gocabs.cabbooking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocabs.cabbooking.entity.Cab;
import com.gocabs.cabbooking.exception.ResourceNotFoundException;
import com.gocabs.cabbooking.repository.CabRepository;

@Service
public class CabServiceImpl implements CabService {
	
	Logger logger = LoggerFactory.getLogger(CabServiceImpl.class);

	@Autowired
	private CabRepository cabRepository;

	public Cab saveCab(Cab cab) {
		Optional<Cab> optionalCab = cabRepository.findByPlateNumber(cab.getPlateNumber());
		if (optionalCab.isPresent()) {
			throw new ResourceNotFoundException("Cab Already Exist With NumberPlate:" + cab.getPlateNumber());
		}
		return cabRepository.save(cab);
	}

	@Override
	public List<Cab> getAllCabs() {
		return cabRepository.findAll();
	}

	@Override
	public Cab getCab(Long id) {
		Optional<Cab> optionalCab = cabRepository.findById(id);
		logger.info("existing cab service method - cabservice class");
		if (optionalCab.isEmpty()) {
			throw new ResourceNotFoundException("Cab Not Found With Id:" + id);
		}
		Cab cab = optionalCab.get();
		return cab;
	}

	@Override
	public Cab updateCab(Cab cab) {
		Optional<Cab> optionalCab = cabRepository.findById(cab.getId());
		if (optionalCab.isEmpty()) {
			throw new ResourceNotFoundException("Cab Not Found With Id:" + cab.getId());
		}
		return cabRepository.save(cab);
	}

	@Override
	public void deleteCab(Long id) {
		Optional<Cab> optionalCab = cabRepository.findById(id);
		if (optionalCab.isEmpty()) {
			throw new ResourceNotFoundException("Cab Not Found With Id:" + id);
		}
		cabRepository.deleteById(id);
	}
}
