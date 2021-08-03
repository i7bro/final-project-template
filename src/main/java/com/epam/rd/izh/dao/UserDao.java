package com.epam.rd.izh.dao;

import com.epam.rd.izh.dto.UserLoginDto;
import com.epam.rd.izh.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(Integer id);

    void update(User entity);

    void save(User entity);


    void delete(Integer id);


    Optional<User> findByLoginAndPassword(UserLoginDto userLoginDto);

    Optional<User> findByLogin(String login);
}
