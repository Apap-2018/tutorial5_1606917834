package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber (String licenseNumber);
	void deletePilot(Long id);
	void addPilot(PilotModel pilot);
	void updatePilot(PilotModel pilot, String licenseNumber);
}