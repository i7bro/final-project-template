package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.TripDto;
import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Trip;

import java.util.List;
import java.util.Optional;

public interface TripServiceI {

    List<Trip> findAll();

    List<Trip> findAllByTourId(Integer tourId);

    List<TripsToursLeftJoinDto> tripsToursLeftJoin();

    List<TripsToursLeftJoinDto> tripsToursLeftJoinByTourId(Integer tourId);

    void delete(Integer id);

    Optional<Trip> findById(Integer id);

    void save(TripDto tripDto);

    void update(TripDto tripDto);
}
