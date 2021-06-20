package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Trip;

import java.util.List;

public interface TripServiceI {

    List<Trip> findAll();

    List<Trip> findAllByTourId(Integer tourId);

    List<TripsToursLeftJoinDto> tripsToursLeftJoin();

    List<TripsToursLeftJoinDto> tripsToursLeftJoinByTourId(Integer tourId);
}
