package com.epam.rd.izh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRequestDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SAVE_SQL =
            "insert into usersRequests (user_id, trip_id) values (?, ?)";

    public void save(Integer userId, Integer trip_id) {
        jdbcTemplate.update(SAVE_SQL, userId, trip_id);
    }
}
