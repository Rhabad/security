package com.security.service.impl;

import com.security.model.dao.UserDao;
import com.security.model.entity.User;
import com.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;
    public List<User> findAllUser(){
        return (List<User>) userDao.findAll();
    }
}
