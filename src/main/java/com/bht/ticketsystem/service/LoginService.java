package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.UserRepository;
import com.bht.ticketsystem.entity.db.User;
import com.bht.ticketsystem.Configure.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String verifyLogin(String userId, String password) throws IOException {
        password = Util.encryptPassword(userId, password);

        String nickname = "";

        User user = userRepository.findById(userId).orElse(null);
        if(user != null && user.getPassword().equals((password))) {
            nickname = user.getNickname();
        }

        return nickname;
    }



}
