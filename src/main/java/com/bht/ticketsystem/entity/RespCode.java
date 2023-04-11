package com.bht.ticketsystem.entity;

public enum RespCode {
    SUCCESS(200, "Success", ""),
    FAIL(0, "Fail", ""),
    ERROR(500, "Error", ""),

    PARAMETER_ERROR(2, "Parameter error", ""),

    URL_ERROR(3, "Url wrong", ""),

    UNAUTHORIZED_ERROR(403, "unauthorized user", ""),
    USER_EXISTS_ERROR(409, "User exists", ""),
    USER_NOT_EXISTS(-1, "User doesn't exist", ""), VERSION_ERROR(-200, "Version collision", "");


    private final Integer resultCode;
    private final String message;

    private final String reason;


    RespCode(Integer resultCode, String message, String reason) {
        this.resultCode = resultCode;
        this.message = message;
        this.reason = reason;
    }

    public Integer getCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}

