package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dto.TripDto;
import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Trip;
import com.epam.rd.izh.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Priority;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TripServiceImplTest {

    @Autowired
    private TripService tripService;

    @Test
    void findAll() {
        List<Trip> all = tripService.findAll();
        assertEquals(6, all.size());
    }

    @Test
    void findAllByTourId() {
        List<Trip> allByTourId = tripService.findAllByTourId(1);
        assertEquals(3, allByTourId.size());
    }

    @Test
    void tripsToursLeftJoin() {
        List<TripsToursLeftJoinDto> tripsToursLeftJoinDtos = tripService.tripsToursLeftJoin();
        assertEquals(6, tripsToursLeftJoinDtos.size());
    }

    @Test
    void tripsToursLeftJoinByTourId() {
        List<TripsToursLeftJoinDto> tripsToursLeftJoinDtos = tripService.tripsToursLeftJoinByTourId(1);
        assertEquals(3, tripsToursLeftJoinDtos.size());
    }

    @Test
    @Transactional
    void delete() {
        tripService.save(new TripDto(null, 1, 100, "2021-07-01T19:00", "main", "helper"));
        int size = tripService.findAll().size();
        tripService.delete(4);
        assertEquals(1, size - tripService.findAll().size());
    }

    @Test
    void findById() {
        Optional<Trip> byId = tripService.findById(1);
        assertNotNull(byId);
    }

    @Test
    void update() {
        Trip byId = tripService.findById(1).get();
        byId.setFreeSpots(100);
        TripDto tripDto = new TripDto(byId.getId(), byId.getTourId(), byId.getFreeSpots(), byId.getArriveDate().toString(),
                byId.getInstructors().get("main"), byId.getInstructors().get("helper"));
        tripService.update(tripDto);
        Trip after = tripService.findById(1).get();
        assertEquals(100, tripDto.getFreeSpots());
    }
}
