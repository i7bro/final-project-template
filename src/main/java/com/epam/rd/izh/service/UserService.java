package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByLogin(String login) {
        User user = userDao.findByLogin(login).orElse(null);
        return userDao.findByLogin(login).orElse(null);
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
}
