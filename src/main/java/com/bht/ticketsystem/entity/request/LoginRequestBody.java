package com.bht.ticketsystem.entity.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class LoginRequestBody {

    @NotNull(message = "user_id cannot be null")
    private final String userId;

    @NotNull(message = "password cannot be null")
    private final String password;


    @JsonCreator
    public LoginRequestBody(@JsonProperty("user_id") String userId, @JsonProperty("password") String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

}
