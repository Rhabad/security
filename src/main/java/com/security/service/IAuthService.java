package com.security.service;

import com.security.model.dto.LoginDto;
import com.security.model.dto.ResponseDto;
import com.security.model.entity.User;

import java.util.HashMap;

public interface IAuthService {

    HashMap<String, String> login(LoginDto loginDto) throws Exception;
    ResponseDto register (User user) throws Exception;
}
