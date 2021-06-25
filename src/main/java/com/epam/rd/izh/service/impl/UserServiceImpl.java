package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No user with this login"));
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public boolean hasSameLogin(User user) {
        return getUserByLogin(user.getLogin()) != null;
    }
}
