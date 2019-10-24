package edu.mum.cs.cs452.safeairlines.service.impl;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.repository.FlightRepository;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }

   @Override
   public Flight saveFlight(Flight flight){
       return flightRepository.save(flight);
   }

   @Override
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
   }

    @Override
    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);

    }

    @Override
    public Flight getFlightById(Long id) {
        return  flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> getFlightBaseOnCriteria(String chain) {
        //return flightRepository.findAllByFlightNumberContainingOrPlaneNumberContainingOrDepaturePlaceContainingOrArrivalPlaceContains(chain,chain,chain,chain);
        return flightRepository.findAllByFlightNumberContainingAndPlaneNumberContaining(chain,chain);
    }

    @Override
    public List<Flight> listFlightForBooking(LocalDate depDate, LocalDate returnDate, Long depPlace, Long ArrivPlace) {

        List<Flight> flights = flightRepository.findAll();
        List<Flight> result = new ArrayList<>();
        for(Flight flight : flights){
            if(flight.getDeptDate().equals(depDate)
            && flight.getDepaturePlace().getId()==depPlace&& flight.getArrivalPlace().getId()==ArrivPlace){
                result.add(flight);
            }
         }

        return result;
    }

    @Override
    public boolean compareDate(LocalDate departureDate, LocalDate arrivalDate) {
        System.out.println(departureDate);
        System.out.println(arrivalDate);
        return  departureDate.isBefore(LocalDate.now()) || arrivalDate.isBefore(departureDate);

    }

    @Override
    public boolean checkDepAndArrPlace(Flight flight) {
        return flight.getDepaturePlace().equals(flight.getArrivalPlace());
    }
}
