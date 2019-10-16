package edu.mum.cs.cs452.safeairlines.service.imp;

import edu.mum.cs.cs452.safeairlines.model.User;
import edu.mum.cs.cs452.safeairlines.repository.UserRepository;
import edu.mum.cs.cs452.safeairlines.service.UserService;
import edu.mum.cs.cs452.safeairlines.utils.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        User newUser=null;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole().getId()== SecurityConstants.ROLE_BUYER) {
            newUser = userRepository.save(user);
        }
        else if (user.getRole().getId()== SecurityConstants.ROLE_ADMIN){
            newUser = userRepository.save(user);
        }
        return newUser;
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
