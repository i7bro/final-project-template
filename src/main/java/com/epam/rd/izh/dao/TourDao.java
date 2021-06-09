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
    private static final String UPDATE_SQL =
            "update tours set title = ?, description = ?, route = ?, cost = ?, notice = ? where id = ?";
    private static final String DELETE_SQL = "delete from tours where id = ?";
    private static final String INSERT_SQL =
            "insert into tours (title, description, route, cost, notice) values (?, ?, ?, ?, ?)";

    public List<Tour> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Tour.class));
    }

    public void updateTour(Tour tour) {
        jdbcTemplate.update(UPDATE_SQL,
                tour.getTitle(), tour.getDescription(), tour.getRoute(), tour.getCost(), tour.getNotice(), tour.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    public void save(Tour tour) {
        jdbcTemplate.update(INSERT_SQL, tour.getTitle(), tour.getDescription(), tour.getRoute(), tour.getCost(), tour.getNotice());
    }

    public Tour findByTitle(String title) {
        return jdbcTemplate.query(FIND_BY_TITLE_SQL, new BeanPropertyRowMapper<>(Tour.class), title).stream()
                .findFirst().orElse(null);
    }
}
