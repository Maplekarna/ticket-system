package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.request.LoginRequestBody;
import com.bht.ticketsystem.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class LoginController {
    public final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody LoginRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = loginService.verifyLogin(requestBody.getUserId(), requestBody.getPassword());


    }

}
