package com.security.model.dao;

import com.security.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Integer> {

    @Query(value = "select * from user where email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
