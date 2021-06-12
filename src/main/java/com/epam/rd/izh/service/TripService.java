package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.TripDao;
import com.epam.rd.izh.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    public final TripDao tripDao;

    @Autowired
    public TripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> findAll() {
        return tripDao.findAll();
    }
}
