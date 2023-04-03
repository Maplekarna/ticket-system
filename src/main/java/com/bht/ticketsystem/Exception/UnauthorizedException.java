package com.bht.ticketsystem.Exception;


public class UnauthorizedException extends Exception {

    public UnauthorizedException() {

    }

    @Override
    public String getMessage() {
        return "Please login.";
    }
}
