package com.example.flightDetails.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightDetails.entity.Flights;
import com.example.flightDetails.repository.FlightsRepo;

@RestController
//@RequestMapping("/api")
public class FlightsController {
	
	@Autowired
	public FlightsRepo flightsRepo;

	@GetMapping("/getFlights")
	public ResponseEntity<List<Flights>> getFlights(){
		
		try {
			List<Flights> list = flightsRepo.findAll();
			if(list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Flights>>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<List<Flights>>(list, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/saveflight")
	public ResponseEntity<Flights> saveFlight(@RequestBody Flights flights) {
		//logic
		try {
			return new ResponseEntity<>(flightsRepo.save(flights), HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getFlights/{id}")
	public ResponseEntity<Flights> getFlight(@PathVariable Long id){
		Optional<Flights> flights = flightsRepo.findById(id);
		if(flights.isPresent()) {
			return new ResponseEntity<Flights>(flights.get(), HttpStatus.OK);
		}else {
			 return new ResponseEntity<Flights>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getFlights/flightNumber")
	public ResponseEntity<List<Flights>> getFlightByNumber(@RequestParam String flightNumber){
		List<Flights> lists = flightsRepo.findByflightNumber(flightNumber);
		return new ResponseEntity<List<Flights>>(lists, HttpStatus.OK);
	}
	
	@GetMapping("/getFlights/originAndDestination")
	public ResponseEntity<List<Flights>> findByOriginAndDestination(@RequestParam String origin, @RequestParam String destination){
		List<Flights> lists = flightsRepo.findByOriginAndDestination(origin, destination);
		return new ResponseEntity<List<Flights>>(lists, HttpStatus.OK);
	}
	
	@PutMapping("/getFlights/{id}")
	public ResponseEntity<Flights> updateFlights(@RequestBody Flights flights){
		try {
			return new ResponseEntity<Flights>(flightsRepo.save(flights), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getFlights/orderAsc")
	public ResponseEntity<List<Flights>> orderedFlights(){
		List<Flights> sortedFlights = flightsRepo.findAll(Sort.by(Sort.Direction.ASC, "duration"));
		return new ResponseEntity<List<Flights>>(sortedFlights, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteFlight/{id}")
	public ResponseEntity<HttpStatus> deleteFlights(@PathVariable Long id){
		try {
			Optional<Flights> flights = flightsRepo.findById(id);
			if(flights.isPresent()) {
				flightsRepo.delete(flights.get());
				
			}
				return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
			
		}catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}








