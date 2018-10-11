package com.apap.tutorial5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	/*
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add (@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight",  flight);
		return "addFlight";
	}
	
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	**/
	
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "id") Long id, Model model) {
		
		flightService.deleteFlight(id);
		return "deleteFlight";
	}
	
	@RequestMapping(value="/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		
		for (FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "deleteFlight";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "id") Long id, Model model) {
		
		FlightModel currFlight = flightService.getFlight(id);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(currFlight.getPilot().getLicenseNumber());
		currFlight.setPilot(pilot);
		model.addAttribute("currFlight", currFlight);
		return "updateFlight";
	}
	
	@RequestMapping(value = "flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight) {
		
		flightService.updateFlight(flight, flight.getId());
		return "flightUpdated";
	}
	
	@RequestMapping(value = "flight/view")
	private String viewFlight(@RequestParam("flightNumber") String flightNumber, Model model) {
		
		List <FlightModel> archiveToGo = new ArrayList();
		List <FlightModel> archive = flightService.getFlightList();
		int i =0;
		for (FlightModel search : archive) {
			if (search.getFlightNumber().equalsIgnoreCase(flightNumber)) {
				archiveToGo.add(search);
				i++;
			}
			
		}
		
		if (i == 0) {
			return "error";
		}
		model.addAttribute("flight_list", archiveToGo);
		return "view-flight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", params= {"addRow"}, method = RequestMethod.POST)
	private String addRow(@ModelAttribute PilotModel pilot, Model model) {
		pilot.getPilotFlight().add(new FlightModel());
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST , params = {"removeRow"})
	private String removeRow(@ModelAttribute PilotModel pilot, final BindingResult bindingResult, 
	        final HttpServletRequest req, Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		pilot.getPilotFlight().remove(rowId.intValue());
		model.addAttribute("pilot", pilot);
		return "addFLight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String addFlight(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		ArrayList<FlightModel> list = new ArrayList<FlightModel>();
		list.add(new FlightModel());
		pilot.setPilotFlight(list);
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"save"})
	private String addFlightSubmit (@ModelAttribute PilotModel pilot) {
		PilotModel currPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(currPilot);
			flightService.addFlight(flight);
		}
		return "add";
	}
	}
