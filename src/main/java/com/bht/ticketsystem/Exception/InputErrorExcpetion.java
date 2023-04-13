package com.bht.ticketsystem.Exception;

public class InputErrorExcpetion extends Exception{
    public InputErrorExcpetion() {

    }

    @Override
    public String getMessage() {
        return "Input Error.";
    }
}
