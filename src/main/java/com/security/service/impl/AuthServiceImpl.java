package com.security.service.impl;

import com.security.model.dao.UserDao;
import com.security.model.dto.LoginDto;
import com.security.model.dto.ResponseDto;
import com.security.model.entity.User;
import com.security.model.validation.UserValidation;
import com.security.service.IAuthService;
import com.security.service.IJWTUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IJWTUtilityService jwtUtilityService;
    @Autowired
    private UserValidation userValidation;

    public HashMap<String, String> login(LoginDto loginDto) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<User> user = userDao.findByEmail(loginDto.getEmail());

            if (user.isEmpty()){
                jwt.put("error", "User no registered!");
                return jwt;
            }

            // verificar contraseña.
            if (verifyPassword(loginDto.getPassword(), user.get().getPassword())){
                jwt.put("jwt", jwtUtilityService.generatedJWt(user.get().getId()));
            } else {
                jwt.put("Error", "Authentication failed");
            }
            return jwt;
        } catch (Exception ex){
            throw new Exception(ex.toString());
        }
    }

    public ResponseDto register (User user) throws Exception{
        try {
            ResponseDto responseDto = userValidation.validate(user);

            if (responseDto.getNumOfError() > 0){
                return responseDto;
            }


            List<User> getAllUsers = (List<User>) userDao.findAll();

            for (User userEntity: getAllUsers){
                if (userEntity != null){
                    responseDto.setNumOfError(1);
                    responseDto.setMessage("User already exists!");
                    return responseDto;
                }
            }

            // encriptr contraseña
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.save(user);
            responseDto.setMessage("User created succesfully!");

            return responseDto;

        }catch (Exception ex){
            throw new Exception(ex.toString());
        }
    }


    private boolean verifyPassword(String enteredPassword, String storedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);

    }


}
