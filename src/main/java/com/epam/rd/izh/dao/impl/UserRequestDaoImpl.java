package com.epam.rd.izh.dao.impl;

import com.epam.rd.izh.dao.UserRequestDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRequestDaoImpl implements UserRequestDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_SQL =
            "insert into usersRequests (user_id, trip_id) values (?, ?)";
    private static final String DELETE_SQL = "delete from usersRequests where id = ?";

    @Override
    public void save(Integer userId, Integer trip_id) {
        jdbcTemplate.update(SAVE_SQL, userId, trip_id);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
