package edu.mum.cs.cs452.safeairlines.service.impl;

import edu.mum.cs.cs452.safeairlines.model.Passenger;
import edu.mum.cs.cs452.safeairlines.repository.PassengerRepository;
import edu.mum.cs.cs452.safeairlines.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {


    @Autowired
    PassengerRepository passengerRepository;


    @Override
    public void save(Passenger passenger) {

        Passenger p = checkExistingPassenger(passenger.getEmail());
        if (p != null) {

            passengerRepository.save(passenger);
        }


    }

    public Passenger checkExistingPassenger(String email) {
        return passengerRepository.getByEmail(email);
    }
}
