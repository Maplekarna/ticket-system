package com.bht.ticketsystem.service;

import com.bht.ticketsystem.dao.UserDao;
import com.bht.ticketsystem.entity.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserDao userDao;

    @Autowired
    private RegisterService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean register(User user) {
        user.setPassword(user.getPassword());
        return userDao.register(user);
    }

}
