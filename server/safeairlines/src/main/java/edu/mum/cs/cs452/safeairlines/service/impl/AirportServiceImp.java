package edu.mum.cs.cs452.safeairlines.service.impl;


import edu.mum.cs.cs452.safeairlines.model.Airport;
import edu.mum.cs.cs452.safeairlines.repository.AirportRepository;
import edu.mum.cs.cs452.safeairlines.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirportServiceImp implements AirportService {

    private AirportRepository airportRepository;

    public  AirportServiceImp(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }
    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public List<Airport> findAllAirport() {
        return airportRepository.findAll();
    }


}
