package com.server.zblog.service;

import com.server.zblog.model.User;
import com.server.zblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object createUser(User reqData) {
        return userRepository.save(reqData);
    }



    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User isDataExist(String username) {
        return userRepository.findByUsername(username);
    }


}
