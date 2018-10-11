package com.apap.tutorial5.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial5.model.FlightModel;

public interface FlightService {
	FlightModel getFlightDetailByLicenseNumber (String licenseNumber);
	FlightModel getFlight(Long id);
	FlightModel findFilghtByFlightNumber(String flightNumber);
	List<FlightModel> getFlightList();
	void deleteFlight(Long id);
	void addFlight(FlightModel flight);
	void updateFlight(FlightModel flight, Long id);
}
