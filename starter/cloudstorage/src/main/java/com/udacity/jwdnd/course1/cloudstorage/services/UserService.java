package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean isUsernameAvailable(String username);

    int createUser(User user);

    User getUser(String username);
}
