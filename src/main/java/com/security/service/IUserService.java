package com.security.service;

import com.security.model.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUser();
}
