package com.example.flightDetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flightDetails.entity.Flights;

@Repository
public interface FlightsRepo extends JpaRepository<Flights, Long> {

	List<Flights> findByflightNumber(String flightNumber);
	
	List<Flights> findByOriginAndDestination(String origin, String destination);
	
}
