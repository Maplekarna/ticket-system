package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.ResultJSONObject;
import com.bht.ticketsystem.entity.db.User;
import com.bht.ticketsystem.service.RegisterService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultJSONObject register(@RequestBody @Validated User user, HttpServletResponse response) throws IOException {
        ResultJSONObject result = null;

        if (!registerService.register(user)) {
            result = ResultJSONObject.userExistsError();
        } else {
            result = ResultJSONObject.success(user);
        }
        return result;
    }
}
