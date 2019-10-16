package edu.mum.cs.cs452.safeairlines.service;

import edu.mum.cs.cs452.safeairlines.model.User;

public interface UserService {

    User saveUser(User user);

    User findUserByUserName(String username);
}