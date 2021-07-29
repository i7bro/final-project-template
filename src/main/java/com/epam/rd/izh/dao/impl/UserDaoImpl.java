package com.epam.rd.izh.dao.impl;

import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.dto.UserLoginDto;
import com.epam.rd.izh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String FIND_ALL_SQL =
            "select id, " +
                    "login, " +
                    "email, " +
                    "first_name, " +
                    "last_name, " +
                    "password, " +
                    "phone, " +
                    "role " +
                    "from users";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " where id = ?";
    private static final String UPDATE_SQL =
            "update users set " +
                    "login = ?, " +
                    "email = ?, " +
                    "first_name = ?, " +
                    "last_name = ?, " +
                    "password = ?, " +
                    "phone = ?, " +
                    "role = ? " +
                    "where id = ?";
    private static final String SAVE_SQL =
            "insert into users (" +
                    "login, " +
                    "email, " +
                    "first_name, " +
                    "last_name, " +
                    "password, " +
                    "phone, " +
                    "role) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "delete from users where id = ?";
    private static final String FIND_BY_LOGIN_SQL =
            "select id, login, email, first_name, last_name, password, phone, role from users where login = ?";
    private static final String FIND_BY_LOGIN_PASS_SQL = FIND_BY_LOGIN_SQL + " and password = ?";

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny();
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(
                UPDATE_SQL,
                entity.getLogin(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPassword(),
                entity.getPhone(),
                entity.getRole().toString(),
                entity.getId()
        );
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(
                SAVE_SQL,
                entity.getLogin(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPassword(),
                entity.getPhone(),
                entity.getRole().toString());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public Optional<User> findByLoginAndPassword(UserLoginDto userLoginDto) {
        return jdbcTemplate.query(FIND_BY_LOGIN_PASS_SQL,
                new BeanPropertyRowMapper<>(User.class),
                userLoginDto.getLogin(),
                userLoginDto.getPassword())

                .stream().findAny();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return jdbcTemplate.query(FIND_BY_LOGIN_SQL, new BeanPropertyRowMapper<>(User.class), login)
                .stream().findAny();
    }
}
