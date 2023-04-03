package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.ResultJSONObject;
import com.bht.ticketsystem.entity.request.LoginRequestBody;
import com.bht.ticketsystem.entity.response.LoginResponseBody;
import com.bht.ticketsystem.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LoginController {
    public final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultJSONObject login(@RequestBody @Validated LoginRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nickname = loginService.verifyLogin(requestBody.getUserId(), requestBody.getPassword());

        ResultJSONObject result;
        if (!nickname.equals("")) {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", requestBody.getUserId());
            session.setMaxInactiveInterval(600);

            result = ResultJSONObject.success(new LoginResponseBody(requestBody.getUserId(), nickname));
        } else {
            result = ResultJSONObject.userNotExistsError();
        }
        return result;

    }

}
