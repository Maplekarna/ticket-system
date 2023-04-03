package com.bht.ticketsystem.service;

import com.bht.ticketsystem.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService {

    private UserDao userDao;

    @Autowired
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }


    public String verifyLogin(String userId, String password) throws IOException {
        return userDao.verifyLogin(userId, password);
    }



}
