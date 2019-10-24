package edu.mum.cs.cs452.safeairlines.repository;

import edu.mum.cs.cs452.safeairlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u where u.email = :email")
    User getByEmail(String email);

     Optional<User> findByEmail(String email);

}
