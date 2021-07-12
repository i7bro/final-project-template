package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.dto.UserSettingsDto;
import com.epam.rd.izh.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    User getUserByLogin(String login);

    void saveUser(User user);

    void saveUser(UserSettingsDto userDto);

    void saveUser(UserPasswordDto userPasswordDto);

    boolean hasSameLogin(User user);

    User getUserById(Integer id);

    boolean checkPassword(UserPasswordDto userPasswordDto);
}
