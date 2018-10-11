package com.apap.tutorial5.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDb;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDb.save(pilot);
	}

	@Override
	public void deletePilot(Long id) {
		// TODO Auto-generated method stub
		pilotDb.deleteById(id);
	}

	@Override
	public void updatePilot(PilotModel pilot, String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel pilotToUpdate = pilotDb.findByLicenseNumber(licenseNumber);
		pilotToUpdate.setName(pilot.getName());
		pilotToUpdate.setFlyHour(pilot.getFlyHour());
		pilotDb.save(pilotToUpdate);
	}
	
}
