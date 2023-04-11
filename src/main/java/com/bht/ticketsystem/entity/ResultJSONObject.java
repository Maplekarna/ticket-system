package com.bht.ticketsystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class ResultJSONObject<R, D> implements Serializable {

    @JsonProperty("resultCode")
    private Integer resultCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("reason")
    private R reason;

    @JsonProperty("data")
    private D data;

    public ResultJSONObject(RespCode respCode) {
        this(respCode, null);

    }


    public ResultJSONObject(RespCode respCode, R reason) {
        this(respCode, reason, null);
    }

    public ResultJSONObject(RespCode respCode, R reason, D data) {
        setResultCode(respCode.getCode());
        setMessage(respCode.getMessage());
        setReason(reason);
        setData(data);
    }

    public static <R, D> ResultJSONObject success(D data) {
        ResultJSONObject result = new ResultJSONObject<>(RespCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResultJSONObject generalError() {
        return new ResultJSONObject(RespCode.ERROR);
    }

    public static ResultJSONObject validFailure(Map<String, Object> reason) {
        return new ResultJSONObject<>(RespCode.PARAMETER_ERROR, reason);
    }


    public static ResultJSONObject noHandlerError(String reason) {
        return new ResultJSONObject<>(RespCode.URL_ERROR, reason);
    }

    public static ResultJSONObject unauthorizedError(String reason) {
        return new ResultJSONObject<>(RespCode.UNAUTHORIZED_ERROR, reason);
    }

    public static ResultJSONObject versionError(String reason) {
        return new ResultJSONObject<>(RespCode.VERSION_ERROR, reason);
    }


    public static ResultJSONObject userExistsError() {
        return new ResultJSONObject<>(RespCode.USER_EXISTS_ERROR);
    }

    public static ResultJSONObject userNotExistsError() {
        return new ResultJSONObject<>(RespCode.USER_NOT_EXISTS);
    }



    private void setResultCode(Integer code) {
        this.resultCode = code;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setReason(R reason) {
        this.reason = reason;
    }

    private void setData(D data) {
        this.data = data;
    }



}
