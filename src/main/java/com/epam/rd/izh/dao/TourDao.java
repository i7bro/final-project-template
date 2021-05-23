package com.epam.rd.izh.dao;

import com.epam.rd.izh.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL =
            "select id, title, description, route, cost, notice from tours";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " where id = ?";
    private static final String FIND_BY_TITLE_SQL = FIND_ALL_SQL + " where title like ?";
    private static final String SAVE_SQL =
            "insert into tours (title, description, route, cost, notice) " +
            "values (?, ?, ?, ?, ?)";

    public List<Tour> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Tour.class));
    }
}
