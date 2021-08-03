package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.dto.UserSettingsDto;
import com.epam.rd.izh.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserByLogin(String login);

    void saveUser(User user);

    void saveUser(UserSettingsDto userDto);

    void saveUser(UserPasswordDto userPasswordDto);

    boolean hasSameLogin(String login);

    User getUserById(Integer id);

    boolean checkPassword(UserPasswordDto userPasswordDto);

    UserPasswordDto getUserPasswordDto(Integer id);

    UserPasswordDto getUserPasswordDto(String login);
}
