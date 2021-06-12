package com.epam.rd.izh.dao;

import com.epam.rd.izh.entity.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class TripDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL = "select id, tour_id, free_spots, arrive_date, instructors from trips";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " where id = ?";
    private static final String FIND_BY_TOUR_ID_SQL = FIND_ALL_SQL + " where tour_id = ?";

    public List<Trip> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new Mapper());
    }

    private static class Mapper implements RowMapper<Trip> {

        @Override
        public Trip mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                return Trip.getBuilder()
                        .id(resultSet.getInt("id"))
                        .tourId(resultSet.getInt("tour_id"))
                        .freeSpots(resultSet.getInt("free_spots"))
                        .arriveDate(resultSet.getString("arrive_date"))
                        .instructors(new ObjectMapper().readValue(resultSet.getString("instructors"), Map.class))
                        .build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
