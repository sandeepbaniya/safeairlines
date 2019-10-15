package edu.mum.cs.cs452.safeairlines.service.imp;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.repository.FlightRepository;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
