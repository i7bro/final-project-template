package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.dao.UserRepository;
import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.dto.UserSettingsDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No user with this login"));
    }

    @Override
    //repo
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    //repo
    public void saveUser(UserSettingsDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(NoSuchElementException::new);

        User newUser = User.builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        userRepository.save(newUser);
    }

    @Override
    //repo
    public void saveUser(UserPasswordDto userPasswordDto) {
        User userById = getUserById(userPasswordDto.getId());

        userById.setPassword(passwordEncoder.encode(userPasswordDto.getNewPass()));
        saveUser(userById);
    }

    @Override
    public boolean hasSameLogin(User user) {
        return getUserByLogin(user.getLogin()) != null;
    }

    @Override
    //repo
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    //repo
    public boolean checkPassword(UserPasswordDto userPasswordDto) {
        User userById = getUserById(userPasswordDto.getId());

        return passwordEncoder.matches(userPasswordDto.getOldPass(), userById.getPassword());
    }
}
