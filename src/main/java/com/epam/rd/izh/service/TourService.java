package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.TourValidDto;
import com.epam.rd.izh.entity.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();

    void updateTour(Tour tour);

    List<List<Object[]>> getAllTourGroups4();

    List<List<Object[]>> getAllTourGroups4(String direction);

    Tour toTour(TourValidDto tourValidDto);

    void delete(Integer id);

    void save(Tour tour);

    Tour findTourByTitle(TourValidDto tourValidDto);

    Tour findTourById(Integer id);
}
