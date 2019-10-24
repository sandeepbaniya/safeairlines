package edu.mum.cs.cs452.safeairlines.service;

import edu.mum.cs.cs452.safeairlines.model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    Flight saveFlight(Flight flight);
    List<Flight> getAllFlights();
    void deleteFlightById(Long id);
    Flight getFlightById(Long id);
    List<Flight> getFlightBaseOnCriteria(String chain);
    List<Flight> listFlightForBooking(LocalDate depDate,LocalDate arrrivDate, Long depPlace, Long ArrivPlace);
    boolean compareDate(LocalDate departureDate, LocalDate arrivalDate);
    boolean checkDepAndArrPlace(Flight flight);

}
