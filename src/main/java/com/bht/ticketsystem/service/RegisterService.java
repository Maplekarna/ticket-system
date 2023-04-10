package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.UserRepository;
import com.bht.ticketsystem.Configure.Util;
import com.bht.ticketsystem.entity.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(User user) throws IOException {
        user.setPassword(Util.encryptPassword(user.getUserId(), user.getPassword()));

        User userFromDB = userRepository.findById(user.getUserId()).orElse(null);
        if (userFromDB != null) return false;

        userRepository.save(user);
        return true;
    }

}
