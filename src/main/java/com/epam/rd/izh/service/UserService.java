package com.epam.rd.izh.service;

import com.epam.rd.izh.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    User getUserByLogin(String login);

    void saveUser(User user);

    boolean hasSameLogin(User user);
}
