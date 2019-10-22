package edu.mum.cs.cs452.safeairlines.service;

import edu.mum.cs.cs452.safeairlines.model.Airport;

import java.util.List;

public interface AirportService {
    Airport saveAirport(Airport airport);
    List<Airport> findAllAirport();
}
