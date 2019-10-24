package edu.mum.cs.cs452.safeairlines.service.impl;

import edu.mum.cs.cs452.safeairlines.model.User;
import edu.mum.cs.cs452.safeairlines.repository.UserRepository;
import edu.mum.cs.cs452.safeairlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    @Override
    public User save(User user) {

        User user1 = checkExistingUser(user.getEmail());
        if (user1 != null) {
            System.out.println("display User");

        }

        return   userRepository.save(user);

//        return  null;
    }

    public User checkExistingUser(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public void sendNotificationRegisterUser(User user) {

    }

    @Override
    public void sendNotficationRegisterFlight(User user) {

    }

    @Override
    public User getUerByMail(String email) {
        return userRepository.findByEmail(email).get();
    }


}
