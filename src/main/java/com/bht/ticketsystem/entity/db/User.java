package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements Serializable {
    @Id

    @NotNull(message = "user_id cannot be null")
    @JsonProperty("user_id")
    private String userId;

    @NotNull(message = "password cannot be null")
    @JsonProperty("password")
    private String password;

    @NotNull(message = "nickname cannot be null")
    @JsonProperty("nickname")
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orderSet;

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User setNickname(String nickname) {
        this.nickname =nickname;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
