package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.TourDao;
import com.epam.rd.izh.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public final class TourService {

    TourDao tourDao;

    @Autowired
    public TourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public List<Tour> getAllTours() {
        return tourDao.findAll();
    }
}
