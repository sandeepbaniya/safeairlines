package edu.mum.cs.cs452.safeairlines.service;

import edu.mum.cs.cs452.safeairlines.model.Passenger;

public interface PassengerService {

    void save(Passenger passenger);
    Passenger checkExistingPassenger(String email);

}
