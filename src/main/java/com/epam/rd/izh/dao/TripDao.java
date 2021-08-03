package com.epam.rd.izh.dao;

import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

public interface TripDao {

    List<Trip> findAll();

    Optional<Trip> findById(Integer id);

    List<Trip> findAllByTourId(Integer tourId);

    List<TripsToursLeftJoinDto> tripsToursLeftJoin();

    List<TripsToursLeftJoinDto> tripsToursLeftJoinByTourId(Integer tourId);

    void delete(Integer id);

    void save(Trip trip);

    void update(Trip trip);
}
