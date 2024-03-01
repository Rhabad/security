package com.security.controller;

import com.security.model.dto.LoginDto;
import com.security.model.dto.ResponseDto;
import com.security.model.entity.User;
import com.security.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private ResponseEntity<ResponseDto> register(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDto loginDto) throws Exception {
        HashMap<String, String> login = authService.login(loginDto);
        if (login.containsKey("jwt")){
            return new ResponseEntity<>(login, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
        }
    }

}
