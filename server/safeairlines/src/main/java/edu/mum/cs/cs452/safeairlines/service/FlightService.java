package edu.mum.cs.cs452.safeairlines.service;

import edu.mum.cs.cs452.safeairlines.model.Flight;

import java.util.List;

public interface FlightService {

    Flight saveFlight(Flight flight);
    List<Flight> getAllFlights();
}
