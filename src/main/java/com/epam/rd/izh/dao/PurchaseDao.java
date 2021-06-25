package com.epam.rd.izh.dao;

import com.epam.rd.izh.dto.PurchaseDto;
import com.epam.rd.izh.util.State;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@AllArgsConstructor
public class PurchaseDao {

    JdbcTemplate jdbcTemplate;

    private static final String USERS_PURCHASE_SQL =
            "select user_request, " +
                    "title, " +
                    "arrive_date, " +
                    "description, " +
                    "direction, " +
                    "route, " +
                    "cost, " +
                    "state " +
            "from (select u.id user_request, " +
                    "u.user_id, " +
                    "u.state, " +
                    "tr.tour_id, " +
                    "tr.arrive_date " +
            "from usersRequests u join trips tr on u.trip_id = tr.id) s " +
                    "join tours t on s.tour_id = t.id where user_id = ?";

    public List<PurchaseDto> findPurchasesByUserId(Integer user_id) {
        return jdbcTemplate.query(USERS_PURCHASE_SQL, new Mapper(), user_id);
    }

    public static class Mapper implements RowMapper<PurchaseDto> {

        @Override
        public PurchaseDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return PurchaseDto.builder()
                    .userRequest(resultSet.getInt("user_request"))
                    .title(resultSet.getString("title"))
                    .arriveDate(resultSet.getString("arrive_date"))
                    .description(resultSet.getString("description"))
                    .direction(resultSet.getString("direction"))
                    .route(resultSet.getInt("route"))
                    .cost(resultSet.getInt("cost"))
                    .state(State.valueOf(resultSet.getString("state")))
                    .build();
        }
    }
}
