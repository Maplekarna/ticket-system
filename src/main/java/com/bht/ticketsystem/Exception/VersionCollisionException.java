package com.bht.ticketsystem.Exception;

public class VersionCollisionException extends Exception {
    public VersionCollisionException() {

    }

    @Override
    public String getMessage() {
        return "Failed, version collision. ";
    }
}
