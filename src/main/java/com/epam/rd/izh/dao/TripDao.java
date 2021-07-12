package com.epam.rd.izh.dao;

import com.epam.rd.izh.dto.TripDto;
import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TripDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SAVE_SQL =
            "insert into trips (tour_id, free_spots, arrive_date, instructors) values (?, ?, ?, ?)";
    private static final String FIND_ALL_SQL =
            "select id, tour_id, free_spots, arrive_date, instructors from trips";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " where id = ?";
    private static final String FIND_BY_TOUR_ID_SQL = FIND_ALL_SQL + " where tour_id = ?";
    private static final String LEFT_JOIN_TRIPS_ON_TOURS =
            "select tr.id, t.id, t.title, tr.arrive_date, tr.free_spots, tr.instructors, t.description, t.direction, t.route, t.cost " +
                    "from trips tr left join tours t on tr.tour_id = t.id";
    private static final String LEFT_JOIN_TRIPS_ON_TOURS_BY_TOUR_ID =
            LEFT_JOIN_TRIPS_ON_TOURS + " where t.id = ?";
    private static final String DELETE_SQL = "delete from trips where id = ?";
    private static final String UPDATE_SQL =
            "update trips set tour_id = ?, free_spots = ?, arrive_date = ?, instructors = ? where id = ?";

    public List<Trip> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new TripMapper());
    }

    public Optional<Trip> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new TripMapper(), id).stream().findAny();
    }

    public List<Trip> findAllByTourId(Integer tourId) {
        return jdbcTemplate.query(FIND_BY_TOUR_ID_SQL, new TripMapper(), tourId);
    }

    public List<TripsToursLeftJoinDto> tripsToursLeftJoin() {
        return jdbcTemplate.query(LEFT_JOIN_TRIPS_ON_TOURS, new TripsToursLeftJoinMapper());
    }

    public List<TripsToursLeftJoinDto> tripsToursLeftJoinByTourId(Integer tourId) {
        return jdbcTemplate.query(LEFT_JOIN_TRIPS_ON_TOURS_BY_TOUR_ID, new TripsToursLeftJoinMapper(), tourId);
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    public void save(Trip trip) {
        try {
            jdbcTemplate.update(SAVE_SQL,
                    trip.getTourId(),
                    trip.getFreeSpots(),
                    trip.getArriveDate(),
                    new ObjectMapper().writeValueAsString(trip.getInstructors()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void update(Trip trip) {
        try {
            jdbcTemplate.update(UPDATE_SQL,
                    trip.getTourId(),
                    trip.getFreeSpots(),
                    trip.getArriveDate(),
                    new ObjectMapper().writeValueAsString(trip.getInstructors()),
                    trip.getId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static class TripMapper implements RowMapper<Trip> {

        @SneakyThrows
        @Override
        public Trip mapRow(ResultSet resultSet, int i) throws SQLException {
            return Trip.builder()
                    .id(resultSet.getInt("id"))
                    .tourId(resultSet.getInt("tour_id"))
                    .freeSpots(resultSet.getInt("free_spots"))
                    .arriveDate(LocalDateTime.parse(resultSet.getString("arrive_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .instructors(new ObjectMapper().readValue(resultSet.getString("instructors"), Map.class))
                    .build();
        }
    }

    private static class TripsToursLeftJoinMapper implements RowMapper<TripsToursLeftJoinDto> {

        @SneakyThrows
        @Override
        public TripsToursLeftJoinDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return TripsToursLeftJoinDto.builder()
                    .tripId(resultSet.getInt("tr.id"))
                    .tourId(resultSet.getInt("t.id"))
                    .title(resultSet.getString("title"))
                    .arriveDate(LocalDateTime.parse(resultSet.getString("arrive_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .freeSpots(resultSet.getInt("free_spots"))
                    .instructors(new ObjectMapper().readValue(resultSet.getString("instructors"), Map.class))
                    .description(resultSet.getString("description"))
                    .direction(resultSet.getString("description"))
                    .route(resultSet.getInt("route"))
                    .cost(resultSet.getInt("cost"))
                    .build();
        }
    }
}
