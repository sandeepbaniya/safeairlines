package edu.mum.cs.cs452.safeairlines.repository;

import edu.mum.cs.cs452.safeairlines.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {


    @Query("SELECT p FROM Passenger p where p.email = :email")
    Passenger getByEmail(String email);
}
