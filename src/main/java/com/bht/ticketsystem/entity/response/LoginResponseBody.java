package com.bht.ticketsystem.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseBody {

    @JsonProperty("user_id")
    private final String userId;

    @JsonProperty("nickname")
    private final String nickname;


    public LoginResponseBody(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

}
