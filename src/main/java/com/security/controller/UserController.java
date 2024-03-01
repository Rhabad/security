package com.security.controller;

import com.security.model.dto.LoginDto;
import com.security.model.entity.User;
import com.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    private ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }
}
