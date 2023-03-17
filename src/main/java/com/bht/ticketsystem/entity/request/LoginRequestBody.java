package com.bht.ticketsystem.entity.request;

public class LoginRequestBody {

    private final String userId;
    private final String password;


    public LoginRequestBody(String userId,  String password) {
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
