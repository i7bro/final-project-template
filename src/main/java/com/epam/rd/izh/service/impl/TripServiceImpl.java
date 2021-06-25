package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.TripDao;
import com.epam.rd.izh.dto.TripDto;
import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Trip;
import com.epam.rd.izh.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

    public final TripDao tripDao;

    @Autowired
    public TripServiceImpl(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Override
    public List<Trip> findAllByTourId(Integer tourId) {
        return tripDao.findAllByTourId(tourId);
    }

    @Override
    public List<TripsToursLeftJoinDto> tripsToursLeftJoin() {
        return tripDao.tripsToursLeftJoin();
    }

    @Override
    public List<TripsToursLeftJoinDto> tripsToursLeftJoinByTourId(Integer tourId) {
        return tripDao.tripsToursLeftJoinByTourId(tourId);
    }

    @Override
    public void delete(Integer id) {
        tripDao.delete(id);
    }

    @Override
    public Optional<Trip> findById(Integer id) {
        return tripDao.findById(id);
    }

    @Override
    public void save(TripDto tripDto) {
        Map<String, String> instructors = new HashMap<>();
        instructors.put("main", tripDto.getMain());
        instructors.put("helper", tripDto.getHelper());

        Trip trip = Trip.getBuilder()
                .arriveDate(LocalDateTime.parse(tripDto.getArriveDate()))
                .freeSpots(tripDto.getFreeSpots())
                .tourId(tripDto.getTourId())
                .instructors(instructors)
                .build();

        tripDao.save(trip);
    }

    @Override
    public void update(TripDto tripDto) {
        Map<String, String> instructors = new HashMap<>();
        instructors.put("main", tripDto.getMain());
        instructors.put("helper", tripDto.getHelper());

        Trip trip = Trip.getBuilder()
                .id(tripDto.getId())
                .arriveDate(LocalDateTime.parse(tripDto.getArriveDate()))
                .freeSpots(tripDto.getFreeSpots())
                .tourId(tripDto.getTourId())
                .instructors(instructors)
                .build();

        tripDao.update(trip);
    }
}
