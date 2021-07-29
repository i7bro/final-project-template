package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.dao.impl.UserDaoImpl;
import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.dto.UserSettingsDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
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
    public void saveUser(UserSettingsDto userDto) {
        User user = userDao.findById(userDto.getId()).orElseThrow(NoSuchElementException::new);

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

        userDao.update(newUser);
    }

    @Override
    public void saveUser(UserPasswordDto userPasswordDto) {
        User userById = getUserById(userPasswordDto.getId());

        userById.setPassword(passwordEncoder.encode(userPasswordDto.getNewPass()));
        userDao.update(userById);
    }

    @Override
    public boolean hasSameLogin(String login) {
        return userDao.findByLogin(login).isPresent();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean checkPassword(UserPasswordDto userPasswordDto) {
        User userById = getUserById(userPasswordDto.getId());

        return passwordEncoder.matches(userPasswordDto.getOldPass(), userById.getPassword());
    }

    @Override
    public UserPasswordDto getUserPasswordDto(Integer id) {
        return UserPasswordDto.builder()
                .id(id)
                .build();
    }

    @Override
    public UserPasswordDto getUserPasswordDto(String login) {
        User userByLogin = getUserByLogin(login);

        return getUserPasswordDto(userByLogin.getId());
    }
}
