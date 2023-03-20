package com.bht.ticketsystem.dao;


import com.bht.ticketsystem.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao {


    private final Map<String, User> userMap;

    @Autowired
    public UserDao() {
        this.userMap = new HashMap<>();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public User getUserByUserId(String userId) {
        return userMap.get(userId);
    }


    public boolean register(User user) {
        String userId = user.getUserId();
        if (userMap.containsKey(userId)) return false;

        userMap.put(userId, user);
        return true;
    }


    public String verifyLogin(String userId, String password) {
        String nickname = "";

        User user = userMap.get(userId);
        if(user != null && user.getPassword().equals((password))) {
            nickname = user.getNickname();
        }

        return nickname;
    }


}
