package com.bht.ticketsystem.entity.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    private String userId;
    private String password;
    private String nickname;

    @OneToMany
    private Set<Order> orderSet;

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User setNickname(String nickname) {
        this.nickname =nickname;
        return this;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }


    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        String str1 = "UserId: " + userId + ", ";
        String str2 = "Nickname: " + nickname + " ";

        return str1 + str2;
    }

}
