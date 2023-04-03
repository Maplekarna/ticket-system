package com.bht.ticketsystem;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public String defaultExceptionHandler(HttpServletRequest resquest, Exception exception) throws Exception{
        logger.warn(exception.getMessage(), exception);
        return "error";
    }
}
