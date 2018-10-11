package com.apap.tutorial5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
	FlightModel findByPilotLicenseNumber (String pilot_licenseNumber);
	FlightModel findFlightById(Long id);
	FlightModel findFlightByFlightNumber(String flightNumber);
}
