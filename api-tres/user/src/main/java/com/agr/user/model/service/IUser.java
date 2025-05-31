package com.agr.user.model.service;

import com.agr.user.model.entity.User;
import java.util.List;

public interface IUser {

    User save(User user);

    User update(User user);

    User findById(Long id);

    List<User> findAll();

    void delete(Long id);

    User login(String usernameOrEmail, String password) throws Exception;

    void logout(Long userId) throws Exception;
}