package com.service;

import com.model.User;

import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    void remove(Long id);
    Optional<User> findUserByEmail(String email);
}
