package com.bht.ticketsystem.Handler;

import com.bht.ticketsystem.Exception.InputErrorExcpetion;
import com.bht.ticketsystem.Exception.UnauthorizedException;
import com.bht.ticketsystem.Exception.VersionCollisionException;
import com.bht.ticketsystem.entity.ResultJSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResultJSONObject defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        logger.warn(exception.getMessage(), exception);
        return ResultJSONObject.generalError();
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResultJSONObject unauthorizedException(UnauthorizedException exception) {
        logger.warn(exception.getMessage(), exception);
        return ResultJSONObject.unauthorizedError(exception.getMessage());
    }

    @ExceptionHandler(value = {VersionCollisionException.class})
    public ResultJSONObject versionCollisionException(VersionCollisionException exception) {
        logger.warn((exception.getMessage()), exception);
        return ResultJSONObject.versionError(exception.getMessage());
    }

    @ExceptionHandler(value = {InputErrorExcpetion.class})
    public ResultJSONObject inputErrorException(InputErrorExcpetion exception) {
        logger.warn((exception.getMessage()), exception);
        return ResultJSONObject.inputError(exception.getMessage());
    }



    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public ResultJSONObject validateException(HttpServletRequest request, BindException bindException) {
        logger.warn(bindException.getMessage(), bindException);
        return ResultJSONObject.validFailure(extractBindInfo(request, bindException));
    }

    private Map<String, Object> extractBindInfo(HttpServletRequest request, BindException bindException) {
        Map<String, Object> message = new HashMap<>();
        bindException.getFieldErrors().forEach(e -> message.put(e.getField(), e.getDefaultMessage()));
        return message;
    }


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResultJSONObject noHandlerFoundException(HttpServletRequest request, NoHandlerFoundException noHandlerFoundException) {
        logger.warn(noHandlerFoundException.getMessage(), noHandlerFoundException);
        return ResultJSONObject.noHandlerError(noHandlerFoundException.getMessage());
    }





}
