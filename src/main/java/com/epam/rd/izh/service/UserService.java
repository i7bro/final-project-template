package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByLogin(String login) {
        return userDao.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No user with this login"));
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public boolean hasSameLogin(User user) {
        return getUserByLogin(user.getLogin()) != null;
    }
}
