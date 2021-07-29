package com.epam.rd.izh.dao.impl;

import com.epam.rd.izh.dao.TourDao;
import com.epam.rd.izh.entity.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TourDaoImpl implements TourDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL =
            "select id, title, description, direction, route, cost, notice from tours";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " where id = ?";
    private static final String FIND_BY_TITLE_SQL = FIND_ALL_SQL + " where title like ?";
    private static final String FIND_BY_DIRECTION = FIND_ALL_SQL + " where direction = ?";
    private static final String SAVE_SQL =
            "insert into tours (title, description, direction, route, cost, notice) " +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "update tours set title = ?, description = ?, direction = ?, route = ?, cost = ?, notice = ? where id = ?";
    private static final String DELETE_SQL = "delete from tours where id = ?";
    private static final String INSERT_SQL =
            "insert into tours (title, description, direction, route, cost, notice) values (?, ?, ?, ?, ?, ?)";

    @Override
    public List<Tour> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Tour.class));
    }

    @Override
    public void updateTour(Tour tour) {
        jdbcTemplate.update(UPDATE_SQL,
                tour.getTitle(), tour.getDescription(), tour.getDirection(), tour.getRoute(), tour.getCost(), tour.getNotice(), tour.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public void save(Tour tour) {
        jdbcTemplate.update(INSERT_SQL, tour.getTitle(), tour.getDescription(), tour.getDirection(), tour.getRoute(), tour.getCost(), tour.getNotice());
    }

    @Override
    public Optional<Tour> findByTitle(String title) {
        return jdbcTemplate.query(FIND_BY_TITLE_SQL, new BeanPropertyRowMapper<>(Tour.class), title).stream()
                .findFirst();
    }

    @Override
    public List<Tour> findAllByDirection(String direction) {
        return jdbcTemplate.query(FIND_BY_DIRECTION, new BeanPropertyRowMapper<>(Tour.class), direction);
    }

    @Override
    public Optional<Tour> findToutById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new BeanPropertyRowMapper<>(Tour.class), id).stream().findFirst();
    }
}
