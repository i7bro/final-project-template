package com.epam.rd.izh.dao;

import com.epam.rd.izh.dto.UserLoginDto;
import com.epam.rd.izh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL =
            "select id, " +
                    "login, " +
                    "email, " +
                    "firstName, " +
                    "lastName, " +
                    "password, " +
                    "phone, " +
                    "role " +
                    "from users";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " where id = ?";
    private static final String UPDATE_SQL =
            "update users set " +
                    "login = ?, " +
                    "email = ?, " +
                    "fistName = ?, " +
                    "lastName = ?, " +
                    "password = ?, " +
                    "phone = ?, " +
                    "role = ? " +
                    "where id = ?";
    private static final String SAVE_SQL =
            "insert into users (" +
                    "login, " +
                    "email, " +
                    "firstName, " +
                    "lastName, " +
                    "password, " +
                    "phone, " +
                    "role) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "delete from users where id = ?";
    private static final String FIND_BY_LOGIN_SQL =
            "select id, login, email, firstName, lastName, password, phone, role from users where login = ?";
    private static final String FIND_BY_LOGIN_PASS_SQL = FIND_BY_LOGIN_SQL + " and password = ?";

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny();
    }

    public void update(User entity) {
        jdbcTemplate.update(
                UPDATE_SQL,
                entity.getLogin(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPassword(),
                entity.getPhone(),
                entity.getRole(),
                entity.getId()
        );
    }

    public User save(User entity) {
        KeyHolder id = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            statement.setString(5, entity.getPassword());
            statement.setString(6, entity.getPhone());
            statement.setString(7, entity.getRole().toString());

            return statement;
        }, id);

        entity.setId(Objects.requireNonNull(id.getKey()).intValue());

        return entity;
    }


    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }


    public Optional<User> findByLoginAndPassword(UserLoginDto userLoginDto) {
        return jdbcTemplate.query(FIND_BY_LOGIN_PASS_SQL,
                new BeanPropertyRowMapper<>(User.class),
                userLoginDto.getLogin(),
                userLoginDto.getPassword())

                .stream().findAny();
    }

    public Optional<User> findByLogin(String login) {
        return jdbcTemplate.query(FIND_BY_LOGIN_SQL, new BeanPropertyRowMapper<>(User.class), login)
                .stream().findAny();
    }
}
