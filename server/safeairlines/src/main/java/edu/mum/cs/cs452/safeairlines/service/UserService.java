package edu.mum.cs.cs452.safeairlines.service;

import edu.mum.cs.cs452.safeairlines.model.User;

public interface UserService {

    User save(User user);
     User checkExistingUser(String email);
    void sendNotificationRegisterUser(User user);
    void sendNotficationRegisterFlight(User user);
    User getUerByMail(String email);


}
