package com.vak.oop.service;

import com.vak.oop.dao.UserDao;
import jakarta.persistence.EntityManager;

public class UserService {
    private final UserDao userDao;

    public UserService(EntityManager entityManager) {
        this.userDao = new UserDao(entityManager);
    }

    public boolean login(String username, String password) {
        if (userDao.isUser(username)) {
            return userDao.loginValidate(username, password);
        }
        return false;
    }
}